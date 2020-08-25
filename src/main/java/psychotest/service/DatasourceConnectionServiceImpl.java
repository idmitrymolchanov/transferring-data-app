package psychotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychotest.config.profile.TomcatConfig;
import psychotest.entity.DatasourceEntity;
import psychotest.entity.DatasourceEntityConnection;
import psychotest.repository.DatasourceConnectionsDAO;
import psychotest.repository.DatasourceConnectionsDAOImpl;
import psychotest.repository.DatasourceDAO;
import psychotest.repository.DatasourceDAOImpl;

import java.util.ArrayList;
import java.util.List;

@Service
public class DatasourceConnectionServiceImpl implements DatasourceConnectionService{
    private final DatasourceDAO dsRepo;
    private final DatasourceConnectionsDAO dscRepo;

    @Autowired
    public DatasourceConnectionServiceImpl(DatasourceDAO dsRepo, DatasourceConnectionsDAO dscRepo) {
        this.dsRepo = dsRepo;
        this.dscRepo = dscRepo;
    }

    @Override
    public List<DatasourceEntity> getSourceTargetConfigs() {
        List<DatasourceEntityConnection> listCon = dscRepo.getAllConnections();
        String source = listCon.get(0).getSource_url();
        String target = listCon.get(0).getTarget_url();
        DatasourceEntity dsSource = dsRepo.getDatasourceByURL(source);
        DatasourceEntity dsTarget = dsRepo.getDatasourceByURL(target);

        List<DatasourceEntity> listDs = new ArrayList<>();
        listDs.add(dsSource);
        listDs.add(dsTarget);

        return listDs;
    }

    @Override
    public boolean ifBothExist(List<DatasourceEntity> list) {
        return (list.get(0) != null && list.get(1) != null);
    }

    @Override
    public void initDatasourceConnection(List<DatasourceEntity> listDs) {
        TomcatConfig tomcatConfig = new TomcatConfig(listDs);
    }
}
