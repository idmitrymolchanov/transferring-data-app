package psychotest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import psychotest.entity.EntitySbertest;
import psychotest.repository.SourceRepository;
import psychotest.repository.TargetRepository;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
public class Scheduler {

    @Autowired
    public TargetRepository targetRepository;

    @Autowired
    public SourceRepository sourceRepository;

    @Scheduled(cron = "0 0 0 * * 5")
    private void storeSourceToTarget() {
        try {
            log.info("Scheduler working: " + LocalDate.now());
            List<EntitySbertest> customers = sourceRepository.findAllSinceLastTargetDate(targetRepository.getLastDate());
            targetRepository.save(customers);
        } catch (Exception e) { }
    }
}