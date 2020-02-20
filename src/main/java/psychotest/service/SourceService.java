package psychotest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import psychotest.entity.EntitySbertest;
import psychotest.repository.SourceRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SourceService {

    private final SourceRepository sourceRepository;

    public List<EntitySbertest> findAllSinceLastTargetDate(LocalDate localDate){
        return sourceRepository.findAllSinceLastTargetDate(localDate);
    }

    public void save(List<EntitySbertest> entitySbertests){
        sourceRepository.save(entitySbertests);
    }

}
