package psychotest.repository;

import psychotest.entity.ValueTypeEntity;

import java.util.List;

public interface TypeValueDAO {
    List<ValueTypeEntity> findByTableName(String tableName);
    void deleteById(Integer id);
    ValueTypeEntity findById(Integer id);
    void saveTypeAndValue(ValueTypeEntity valueTypeEntity);
    void deleteTableById(Integer id);
}
