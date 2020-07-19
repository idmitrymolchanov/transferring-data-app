package psychotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychotest.entity.TableNameEntity;
import psychotest.repository.HelpRepository;

import java.util.List;

@Service
public class HelpNameService {
    private final HelpRepository helpRepository;

    @Autowired
    public HelpNameService(HelpRepository helpRepository) {
        this.helpRepository = helpRepository;
    }

    public void saveTableName(TableNameEntity tableNameEntity) {
        helpRepository.saveTableName(tableNameEntity);
    }

    public String findLastByName() {
        return helpRepository.findLastByName();
    }

    public void deleteTableById(Integer id) {
        helpRepository.deleteTableById(id);
    }

    public List<TableNameEntity> getAllTable() {
        return helpRepository.getAllTable();
    }
}
