package psychotest.service;

import psychotest.entity.DatasourceEntity;
import psychotest.entity.DatasourceEntityConnection;

import java.util.List;

public interface DatasourceConnectionService {
    List<DatasourceEntity> getSourceTargetConfigs();
    boolean ifBothExist(List<DatasourceEntity> list);
    void initDatasourceConnection(List<DatasourceEntity> listDs);
    void saveConnections(DatasourceEntityConnection datasource);
    String getHashById(String id);
    List<DatasourceEntityConnection> getAllConn();
}
