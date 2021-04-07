package psychotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychotest.entity.ValueTypeEntity;
import psychotest.repository.TypeValueDAO;
import psychotest.repository.TypeValueDAOImpl;
import psychotest.repository.base.SourceDAO;

import java.util.List;

@Service
public class TypeValueServiceImpl implements TypeValueService {
    private final TypeValueDAO typeValueDAO;
    private final SourceDAO sourceDAO;

    @Autowired
    public TypeValueServiceImpl(TypeValueDAO typeValueDAO, SourceDAO sourceDAO) {
        this.typeValueDAO = typeValueDAO;
        this.sourceDAO = sourceDAO;
    }

    @Override
    public List<ValueTypeEntity> findByTableName(String tableName) {
        return typeValueDAO.findByTableName(tableName);
    }

    @Override
    public void deleteById(Integer id) {
        typeValueDAO.deleteById(id);
    }

    @Override
    public ValueTypeEntity findById(Integer id) {
        return typeValueDAO.findById(id);
    }

    @Override
    public void saveTypeAndValue(ValueTypeEntity valueTypeEntity) {
        typeValueDAO.saveTypeAndValue(valueTypeEntity);
    }

    @Override
    public List<String> getColumnsNames(String tableName) {
        return sourceDAO.getColumnsNames(tableName);
    }

    @Override
    public List<String> getColumnsTypes(String tableName) {
        return sourceDAO.getColumnsTypes(tableName);
    }
}
