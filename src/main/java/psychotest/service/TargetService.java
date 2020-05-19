package psychotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychotest.entity.EntitySbertest;
import psychotest.repository.TargetRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class TargetService {

    public final TargetRepository targetRepository;

    @Autowired
    public TargetService(TargetRepository targetRepository) {
        this.targetRepository = targetRepository;
    }

    public void saveAll(List<EntitySbertest> entitySbertests){
        targetRepository.save(entitySbertests);
    }

    public LocalDate getLastDate(){
        return targetRepository.getLastDate();
    }

    public List<EntitySbertest> findById(Long id){
        return targetRepository.findById(id);
    }

}
