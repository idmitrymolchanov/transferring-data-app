package psychotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychotest.entity.UniqueValuesEntity;
import psychotest.repository.UniqueValueDAO;
import psychotest.repository.UniqueValueDAOImpl;

@Service
public class UniqueValueServiceImpl implements UniqueValueService {
    private final UniqueValueDAO uniqueValueDAO;

    @Autowired
    public UniqueValueServiceImpl(UniqueValueDAO uniqueValueDAO) {
        this.uniqueValueDAO = uniqueValueDAO;
    }

    @Override
    public void saveUniqueValue(UniqueValuesEntity value) {
        uniqueValueDAO.saveUniqueValue(value);
    }
}
