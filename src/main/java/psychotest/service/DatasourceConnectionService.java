package psychotest.service;

import psychotest.entity.DatasourceEntity;

import java.util.List;

public interface DatasourceConnectionService {
    List<DatasourceEntity> getSourceTargetConfigs();
    boolean ifBothExist(List<DatasourceEntity> list);
    void initDatasourceConnection(List<DatasourceEntity> listDs);
}
