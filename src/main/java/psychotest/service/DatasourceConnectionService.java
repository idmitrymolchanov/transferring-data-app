package psychotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychotest.config.profile.TomcatConfig;
import psychotest.entity.DatasourceEntity;
import psychotest.entity.DatasourceEntityConnection;
import psychotest.repository.DatasourceConnectionsRepo;
import psychotest.repository.DatasourceRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class DatasourceConnectionService {
    private final DatasourceRepository dsRepo;
    private final DatasourceConnectionsRepo dscRepo;

    @Autowired
    public DatasourceConnectionService (DatasourceRepository dsRepo, DatasourceConnectionsRepo dscRepo) {
        this.dsRepo = dsRepo;
        this.dscRepo = dscRepo;
    }

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

    public boolean ifBothExist(List<DatasourceEntity> list) {
        return (list.get(0) != null && list.get(1) != null);
    }

    public void initDatasourceConnection(List<DatasourceEntity> listDs) {
        TomcatConfig tomcatConfig = new TomcatConfig(listDs);
    }
}
