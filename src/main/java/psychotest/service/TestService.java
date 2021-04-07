package psychotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychotest.config.profile.ConfigLocal;
import psychotest.entity.DatasourceEntity;
import psychotest.entity.DatasourceEntityConnection;
import psychotest.repository.base.SourceNEW;

import java.util.*;

@Service
public class TestService {
    private final SourceNEW sourceDAO;
    private final DatasourceConnectionService datasourceConnectionService;

    @Autowired
    public TestService(SourceNEW sourceDAO, DatasourceConnectionService datasourceConnectionService) {
        this.sourceDAO = sourceDAO;
        this.datasourceConnectionService = datasourceConnectionService;
    }

//    @Scheduled(fixedRate = 9000)
    public void test_test() {
        System.out.println("in method");
        DatasourceEntityConnection entityConnection = datasourceConnectionService.getConnById(5L);
        List<DatasourceEntity> entityList = datasourceConnectionService.getSourceTargetConfigs(entityConnection);

        ConfigLocal.datasourceEntitySource = entityList.get(1);

        List<String> names = sourceDAO.getNameTables1();
        System.out.println(names);
        List<String> n = sourceDAO.getColumnsNames("two");
        System.out.println(n);
        List<String> t = sourceDAO.getColumnsTypes("two");
        System.out.println(t);
    }
}
