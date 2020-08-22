package psychotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychotest.entity.DatasourceEntity;
import psychotest.repository.DatasourceRepository;

@Service
public class DatasourceService {

    private final DatasourceRepository datasourceRepository;

    @Autowired
    public DatasourceService(DatasourceRepository datasourceRepository) {
        this.datasourceRepository = datasourceRepository;
    }

    public void saveDatasource(DatasourceEntity datasourceEntity) {
        datasourceRepository.saveDatasourceTable(datasourceEntity);
    }

    public DatasourceEntity getDatasourceById(Integer id) {
        return datasourceRepository.getDatasourceById(id);
    }

    public String findLastDataSourceByUrl() {
        return datasourceRepository.findLastDataSourceByUrl();
    }
}
