package psychotest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychotest.entity.DatasourceEntity;
import psychotest.entity.DatasourceEntityConnection;
import psychotest.repository.DatasourceConnectionsDAO;
import psychotest.repository.DatasourceDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class DatasourceConnectionServiceImpl implements DatasourceConnectionService{
    private final DatasourceDAO dsRepo;
    private final DatasourceConnectionsDAO dscRepo;

    @Autowired
    public DatasourceConnectionServiceImpl(DatasourceDAO dsRepo, DatasourceConnectionsDAO dscRepo) {
        this.dsRepo = dsRepo;
        this.dscRepo = dscRepo;
    }

    @Override
    public List<DatasourceEntity> getSourceTargetConfigs(DatasourceEntityConnection entityConnection) {
        try {
            String source = entityConnection.getSource_url();
            String target = entityConnection.getTarget_url();

            DatasourceEntity dsSource = dsRepo.getDatasourceByURL(source);
            DatasourceEntity dsTarget = dsRepo.getDatasourceByURL(target);

            List<DatasourceEntity> listDs = new ArrayList<>();
            listDs.add(dsSource);
            listDs.add(dsTarget);
            return listDs;

        } catch (NullPointerException | IndexOutOfBoundsException e) {
            log.error("no target or source in database for current connection");
            return Collections.emptyList();
        }
    }

    @Override
    public void saveConnections(DatasourceEntityConnection datasource) {
        datasource.setHash_connection(createHashConnection());
        dscRepo.saveDatasourceTable(datasource);
    }

    @Override
    public String getHashById(String id) {
        return dscRepo.getHashConnection(Long.parseLong(id));
    }

    @Override
    public List<DatasourceEntityConnection> getAllConn() {
        return dscRepo.getAllConnections();
    }

    @Override
    public DatasourceEntityConnection getConnById(Long id) {
        return dscRepo.getConnectionsById(id);
    }

    private String createHashConnection() {
        int firstNumber = (int)(Math.random()*1000);
        int secondNumber = (int)(Math.random()*1000);
        return firstNumber + "salt_con" + secondNumber;
    }
}
