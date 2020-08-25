package psychotest.service;

import psychotest.entity.DatasourceEntity;

public interface DatasourceService {
    void saveDatasource(DatasourceEntity datasourceEntity);
    DatasourceEntity getDatasourceById(Integer id);
    String findLastDataSourceByUrl();
}
