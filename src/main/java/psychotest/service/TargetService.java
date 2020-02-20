package psychotest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import psychotest.entity.EntitySbertest;
import psychotest.repository.TargetRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TargetService {

    private final TargetRepository targetRepository;

    public void saveAll(List<EntitySbertest> entitySbertests){
        targetRepository.save(entitySbertests);
    }

    public LocalDate getLastDate(){
        return targetRepository.getLastDate();
    }

}
