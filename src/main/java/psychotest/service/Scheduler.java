package psychotest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import psychotest.entity.EntitySbertest;
import psychotest.repository.SourceRepository;
import psychotest.repository.TargetRepository;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class Scheduler {

    private final TargetRepository targetRepository;
    private final SourceRepository sourceRepository;

    @Autowired
    public Scheduler(TargetRepository targetRepository, SourceRepository sourceRepository) {
        this.targetRepository = targetRepository;
        this.sourceRepository = sourceRepository;
    }

    /**
     * determine the date of the last record in "target"DB.
     * Based on it, we are looking for records in "source"DB that appeared later.
     * If there are such records, copy them to "target"DB
     */
    @Scheduled(cron = "0 0 0 * * 5")
    private void storeSourceToTarget() {
        try {
            log.info("Scheduler working: " + LocalDate.now());
            List<EntitySbertest> entitySbertestList = sourceRepository.findAllSinceLastTargetDate(targetRepository.getLastDate());
            targetRepository.save(entitySbertestList);
        } catch (Exception e) { }
    }
}