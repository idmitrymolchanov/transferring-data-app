package psychotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychotest.entity.TableNameEntity;
import psychotest.repository.RowTypeValueRepository;
import psychotest.repository.TableNameRepository;

import java.util.List;

@Service
public class TableNameService {
    private final RowTypeValueRepository rowTypeValueRepository;
    private final TableNameRepository tableNameRepository;

    @Autowired
    public TableNameService(RowTypeValueRepository rowTypeValueRepository, TableNameRepository tableNameRepository) {
        this.rowTypeValueRepository = rowTypeValueRepository;
        this.tableNameRepository = tableNameRepository;
    }

    public void saveTableName(TableNameEntity tableNameEntity) {
        tableNameRepository.saveTableName(tableNameEntity);
    }

    public String findLastByName() {
        return tableNameRepository.findLastByName();
    }

    public void deleteTableById(Integer id) {
        rowTypeValueRepository.deleteTableById(id);
    }

    public List<TableNameEntity> getAllTable() {
        return tableNameRepository.getAllTable();
    }
}
