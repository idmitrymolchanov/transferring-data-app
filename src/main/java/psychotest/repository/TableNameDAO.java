package psychotest.repository;

import psychotest.entity.TableNameEntity;

import java.util.List;

public interface TableNameDAO {
    void saveTableName(TableNameEntity tableNameEntity);
    String findLastByName();
    List<TableNameEntity> getAllTable();
    List<String> getAllTableByHashConnection(String hashConnection);
    String getTableNameByTableHash(String tableHash);
}
