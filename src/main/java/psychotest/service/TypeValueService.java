package psychotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychotest.entity.ValueTypeEntity;
import psychotest.repository.RowTypeValueRepository;

import java.util.List;

@Service
public class TypeValueService {
    private final RowTypeValueRepository rowTypeValueRepository;

    @Autowired
    public TypeValueService(RowTypeValueRepository rowTypeValueRepository) {
        this.rowTypeValueRepository = rowTypeValueRepository;
    }

    public List<ValueTypeEntity> findByTableName(String tableName) {
        return rowTypeValueRepository.findByTableName(tableName);
    }

    public void deleteById(Integer id) {
        rowTypeValueRepository.deleteById(id);
    }

    public ValueTypeEntity findById(Integer id) {
        return rowTypeValueRepository.findById(id);
    }

    public void saveTypeAndValue(ValueTypeEntity valueTypeEntity) {
        rowTypeValueRepository.saveTypeAndValue(valueTypeEntity);
    }
}
