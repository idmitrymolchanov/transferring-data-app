package psychotest.service;

import psychotest.entity.TableNameEntity;

import java.util.List;

public interface TableNameService {
    void saveTableName(TableNameEntity tableNameEntity);
    String findLastByName();
    void deleteTableById(Integer id);
    List<TableNameEntity> getAllTable();
}
