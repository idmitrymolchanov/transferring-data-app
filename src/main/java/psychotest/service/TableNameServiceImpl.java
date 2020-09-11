package psychotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychotest.entity.TableNameEntity;
import psychotest.inner_datasource.config.stack.Pull;
import psychotest.inner_datasource.config.stack.TablesPull;
import psychotest.repository.TableNameDAO;
import psychotest.repository.TypeValueDAO;

import java.util.List;

@Service
public class TableNameServiceImpl implements TableNameService {
    private final TypeValueDAO typeValueDAO;
    private final TableNameDAO tableNameDAO;
    private String hashTable;
    private Pull pull = TablesPull.getInstance();

    @Autowired
    public TableNameServiceImpl(TypeValueDAO typeValueDAO, TableNameDAO tableNameDAO) {
        this.typeValueDAO = typeValueDAO;
        this.tableNameDAO = tableNameDAO;
    }

    @Override
    public void saveTableName(TableNameEntity tableNameEntity) {
        hashTable = createHashTable();
        tableNameEntity.setHash_table_name(hashTable);
        tableNameDAO.saveTableName(tableNameEntity);
        pull.push(hashTable);
    }

    @Override
    public String findLastByName() {
        return tableNameDAO.findLastByName();
    }

    @Override
    public void deleteTableById(Integer id) {
        typeValueDAO.deleteTableById(id);
    }

    @Override
    public List<TableNameEntity> getAllTable() {
        return tableNameDAO.getAllTable();
    }

    private String createHashTable() {
        int firstNumber = (int)(Math.random()*1000);
        int secondNumber = (int)(Math.random()*1000);
        return firstNumber + "salt" + secondNumber;
    }
}
