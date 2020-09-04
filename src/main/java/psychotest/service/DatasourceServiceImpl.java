package psychotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychotest.entity.DatasourceEntity;
import psychotest.repository.DatasourceDAO;

@Service
public class DatasourceServiceImpl implements DatasourceService {

    private final DatasourceDAO datasourceDAO;

    @Autowired
    public DatasourceServiceImpl(DatasourceDAO datasourceDAO) {
        this.datasourceDAO = datasourceDAO;
    }

    @Override
    public void saveDatasource(DatasourceEntity datasourceEntity) {
        datasourceDAO.saveDatasourceTable(datasourceEntity);
    }

    @Override
    public DatasourceEntity getDatasourceById(Integer id) {
        return datasourceDAO.getDatasourceById(id);
    }

    @Override
    public String findLastDataSourceByUrl() {
        return datasourceDAO.findLastDataSourceByUrl();
    }
}
