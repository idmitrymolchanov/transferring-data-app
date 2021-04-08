package psychotest.service;

import psychotest.entity.DatasourceEntity;
import psychotest.entity.DatasourceEntityConnection;

import java.util.List;

public interface DatasourceConnectionService {
    List<DatasourceEntity> getSourceTargetConfigs(DatasourceEntityConnection entityConnection);
    void saveConnections(DatasourceEntityConnection datasource);
    String getHashById(String id);
    List<DatasourceEntityConnection> getAllConn();
    DatasourceEntityConnection getConnById(Long id);
}
