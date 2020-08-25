package psychotest.repository;

import psychotest.entity.ValueTypeEntity;

import java.util.List;

public interface MainRequestDAO {
    List<ValueTypeEntity> getTypeAndValue(String tableName);
    String getUniqueValue(String tableName);
}
