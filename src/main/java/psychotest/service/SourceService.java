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

    public List<EntitySbertest> findAllSinceLastTargetDate(LocalDate localDate){
        return sourceRepository.findAllSinceLastTargetDate(localDate);
    }

    public void save(List<EntitySbertest> entitySbertests){
        sourceRepository.save(entitySbertests);
    }

    public List<EntitySbertest> findById(Long id){
        return sourceRepository.findById(id);
    }
}
