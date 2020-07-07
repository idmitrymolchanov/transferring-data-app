package psychotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychotest.entity.UniqueValuesEntity;
import psychotest.entity.HelpValueAndTypeEntity;
import psychotest.repository.HelpRepository;

import java.util.List;

@Service
public class HelpValueService {
    private final HelpRepository helpRepository;

    @Autowired
    public HelpValueService(HelpRepository helpRepository) {
        this.helpRepository = helpRepository;
    }

    public List<HelpValueAndTypeEntity> findByTableName(String tableName) {
        return helpRepository.findByTableName(tableName);
    }

    public void deleteById(Integer id) {
        helpRepository.deleteById(id);
    }

    public HelpValueAndTypeEntity findById(Integer id) {
        return helpRepository.findById(id);
    }

    public void saveTypeAndValue(HelpValueAndTypeEntity helpValueAndTypeEntity) {
        helpRepository.saveTypeAndValue(helpValueAndTypeEntity);
    }

    public void saveUniqueValue(UniqueValuesEntity value) {
        helpRepository.saveUniqueValue(value);
    }
}
