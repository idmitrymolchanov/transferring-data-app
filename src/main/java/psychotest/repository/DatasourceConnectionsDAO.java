package psychotest.repository;

import psychotest.entity.DatasourceEntityConnection;

import java.util.List;

public interface DatasourceConnectionsDAO {
    void saveDatasourceTable(DatasourceEntityConnection datasource);
    List<DatasourceEntityConnection> getAllConnections();
    DatasourceEntityConnection getConnectionsById(Long id);
    String getHashConnection(Long id);

}
