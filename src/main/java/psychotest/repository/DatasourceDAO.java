package psychotest.repository;

import psychotest.entity.DatasourceEntity;

import java.util.List;

public interface DatasourceDAO {
    void saveDatasourceTable(DatasourceEntity datasourceEntity);
    DatasourceEntity getDatasourceById(Integer id);
    DatasourceEntity getDatasourceByURL(String url);
    String findLastDataSourceByUrl();
    List<String> getDatasourceBySourceRole(String sourceRole);
}
