package psychotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychotest.entity.EntitySbertest;
import psychotest.repository.SourceRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class SourceService {

    @Autowired
    public SourceRepository sourceRepository;

    public List<EntitySbertest> getAll(){
        return sourceRepository.getDataCall();
    }

    public List<EntitySbertest> getAllSinceCurrentDate(LocalDate localDate){
        return sourceRepository.getAllSinceCurrentDate(localDate);
    }

    public void saveAll(List<EntitySbertest> entitySbertests){
        sourceRepository.saveDataCall(entitySbertests);
    }
}
