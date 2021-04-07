package psychotest.service;

import psychotest.entity.ValueTypeEntity;

import java.util.List;

public interface TypeValueService {
    List<ValueTypeEntity> findByTableName(String tableName);
    void deleteById(Integer id);
    ValueTypeEntity findById(Integer id);
    void saveTypeAndValue(ValueTypeEntity valueTypeEntity);
    List<String> getColumnsNames(String tableName);
    List<String> getColumnsTypes(String tableName);
}
