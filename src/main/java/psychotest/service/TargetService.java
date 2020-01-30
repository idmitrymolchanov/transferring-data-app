package psychotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychotest.entity.EntitySbertest;
import psychotest.repository.TargetRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class TargetService {

    @Autowired
    public TargetRepository targetRepository;

    public List<EntitySbertest> getAll(){
        return targetRepository.getDataCall();
    }

    public void saveAll(List<EntitySbertest> entitySbertests){
        targetRepository.saveDataCall(entitySbertests);
    }

    public LocalDate getLastDate(){
        return targetRepository.getLastDate();
    }

    public List<EntitySbertest> getAllById(Long id){
        return targetRepository.getAllById(id);
    }

}
