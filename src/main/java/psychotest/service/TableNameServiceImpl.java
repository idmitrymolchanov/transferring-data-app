package psychotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychotest.entity.TableNameEntity;
import psychotest.repository.TableNameDAO;
import psychotest.repository.TypeValueDAO;
import psychotest.repository.TypeValueDAOImpl;
import psychotest.repository.TableNameDAOImpl;

import java.util.List;

@Service
public class TableNameServiceImpl implements TableNameService {
    private final TypeValueDAO typeValueDAO;
    private final TableNameDAO tableNameDAO;

    @Autowired
    public TableNameServiceImpl(TypeValueDAO typeValueDAO, TableNameDAO tableNameDAO) {
        this.typeValueDAO = typeValueDAO;
        this.tableNameDAO = tableNameDAO;
    }

    @Override
    public void saveTableName(TableNameEntity tableNameEntity) {
        tableNameDAO.saveTableName(tableNameEntity);
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
}
