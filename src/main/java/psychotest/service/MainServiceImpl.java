package psychotest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import psychotest.config.profile.ConfigLocal;
import psychotest.entity.DatasourceEntity;
import psychotest.entity.DatasourceEntityConnection;
import psychotest.entity.ValueTypeEntity;
import psychotest.repository.MainRequestDAO;
import psychotest.repository.base.SourceDAO;
import psychotest.repository.base.TargetDAO;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MainServiceImpl implements MainService {

    private final MainRequestDAO mainRequestDAO;
    private final TargetDAO targetDAO;
    private final SourceDAO sourceDAO;
    private final DatasourceConnectionService dscService;
    private final TableNameService tableNameService;

    @Autowired
    public MainServiceImpl(MainRequestDAO mainRequestDAO, TargetDAO targetDAO, SourceDAO sourceDAO,
                           DatasourceConnectionService dscService, TableNameService tableNameService) {
        this.mainRequestDAO = mainRequestDAO;
        this.targetDAO = targetDAO;
        this.sourceDAO = sourceDAO;
        this.dscService = dscService;
        this.tableNameService = tableNameService;
    }

    static final int i = 900000;

    @Scheduled(fixedRate = i)
    @Override
    public void mainMethodScheduler() {
        // get all connections (in pairs)
        List<DatasourceEntityConnection> entityConnectionList = dscService.getAllConn();
        for(DatasourceEntityConnection entityConnection : entityConnectionList) {
            // get source and target in current connection
            List<DatasourceEntity> entityList = dscService.getSourceTargetConfigs(entityConnection);
            // change datasource beans
            ConfigLocal.datasourceEntitySource = entityList.get(0);
            ConfigLocal.datasourceEntityTarget = entityList.get(1);
            // change jdbcTemplate beans
            sourceDAO.refreshCustomJdbc();
            targetDAO.refreshCustomJdbc();
            // get table list to transfer data for current connection
            List<String> tablesList = tableNameService.getAllTableByHashConn(entityConnection.getHash_connection());
            // iterate this list
            for(String hashTable : tablesList) {
                // get columns types and values
                List<ValueTypeEntity> columnsList = mainRequestDAO.getTypeAndValue(hashTable);
                // list for column values
                List<String> valueList = new ArrayList<>();
                // list for column types
                List<String> typeList = new ArrayList<>();
                // separate values from main list of t and v
                for (ValueTypeEntity valueTypeEntity : columnsList) {
                    valueList.add(valueTypeEntity.getStringValue());
                    typeList.add(valueTypeEntity.getStringType());
                }
                // get name of unique column
                String uniqueString = mainRequestDAO.getUniqueValue(hashTable);
                // get table name
                String tableName = tableNameService.getTableNameByTableHash(hashTable);
                // get value of name of unique column in real table
                String uniqueValue = targetDAO.getLastValue(tableName, uniqueString);
                // get last row in source
                int lastRow = sourceDAO.getNumberLastString(tableName, uniqueValue, uniqueString, valueList);
                // load rows from source !
                List<List<String>> newValuesFromTarget = sourceDAO.findAllSinceLastTargetActivity(lastRow, valueList, tableName);
                // save rows from source to target !
                targetDAO.saveAll(newValuesFromTarget, typeList, valueList, tableName);
                System.out.println("success");
            }
        }
    }
}
