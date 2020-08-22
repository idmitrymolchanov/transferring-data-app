package psychotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychotest.entity.UniqueValuesEntity;
import psychotest.repository.UniqueValueRepository;

@Service
public class UniqueValueService {
    private final UniqueValueRepository uniqueValueRepository;

    @Autowired
    public UniqueValueService(UniqueValueRepository uniqueValueRepository) {
        this.uniqueValueRepository = uniqueValueRepository;
    }

    public void saveUniqueValue(UniqueValuesEntity value) {
        uniqueValueRepository.saveUniqueValue(value);
    }
}
