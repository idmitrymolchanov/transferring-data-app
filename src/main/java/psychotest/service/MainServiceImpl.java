package psychotest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import psychotest.config.profile.ConfigLocal;
import psychotest.entity.DatasourceEntity;
import psychotest.entity.DatasourceEntityConnection;
import psychotest.entity.SchedulerEntity;
import psychotest.entity.ValueTypeEntity;
import psychotest.repository.MainRequestDAO;
import psychotest.repository.base.SourceDAO;
import psychotest.repository.base.TargetDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
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
    private final SchedulerService schedulerService;

    private LocalDate currentDate;
    private SimpleDateFormat sdfDateDMY;
    static final int i = 900000;

    @Autowired
    public MainServiceImpl(MainRequestDAO mainRequestDAO, TargetDAO targetDAO, SourceDAO sourceDAO,
                           DatasourceConnectionService dscService, TableNameService tableNameService,
                           SchedulerService schedulerService) {
        this.mainRequestDAO = mainRequestDAO;
        this.targetDAO = targetDAO;
        this.sourceDAO = sourceDAO;
        this.dscService = dscService;
        this.tableNameService = tableNameService;
        this.schedulerService = schedulerService;
    }

    public void setDateParameters() {
        currentDate = LocalDate.now();
        sdfDateDMY = new SimpleDateFormat("dd/MM/yyyy");
    }

    @Scheduled(fixedRate = i)
    @Override
    public void mainMethodScheduler() throws ParseException {
        int date;
        setDateParameters();

        // get all connections (in pairs)
        List<DatasourceEntityConnection> entityConnectionList = dscService.getAllConn();
        for(DatasourceEntityConnection entityConnection : entityConnectionList) {
            // get source and target in current connection
            List<DatasourceEntity> entityList = dscService.getSourceTargetConfigs(entityConnection);
            log.info("get source and target in current connection - done");
            // change datasource beans
            ConfigLocal.datasourceEntitySource = entityList.get(0);
            ConfigLocal.datasourceEntityTarget = entityList.get(1);
            // change jdbcTemplate beans
            sourceDAO.refreshCustomJdbc();
            targetDAO.refreshCustomJdbc();
            log.info("change jdbcTemplate beans - done");
            // get table list to transfer data for current connection
            List<String> tablesList = tableNameService.getAllTableByHashConn(entityConnection.getHash_connection());
            log.info("get table list to transfer data - done");
            // iterate this list
            for(String hashTable : tablesList) {
                // find scheduler object
                SchedulerEntity scheduler = schedulerService.findObjectByTableHash(hashTable);
                log.info("find scheduler object - done");
                // get date for first run
                LocalDate localDate = sdfDateDMY.parse(scheduler.getDateValue()).toInstant().
                        atZone(ZoneId.systemDefault()).toLocalDate();
                date = currentDate.compareTo(localDate);
                if(date < 0) {
                    log.info("selected task is unnecessary by date");
                    continue;
                }
                else if(date > 0) {
                    if(scheduler.getRemainder() != 0) {
                        log.info("selected task is unnecessary by remainder");
                        schedulerService.updateRemainder(hashTable);
                        continue;
                    }
                }

                transferData(hashTable);
                log.info("data transfer for " + hashTable + " - done");
                schedulerService.updateRemainder(hashTable);
            }
        }
    }

    private void transferData(String hashTable) {
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

        List<List<String>> newValuesFromSource;
        if(uniqueValue == null) {
            // load all rows from source !
            newValuesFromSource = sourceDAO.findAll(valueList, tableName);
        } else {
            // get last row in source
            int lastRow = sourceDAO.getNumberLastString(tableName, uniqueValue, uniqueString, valueList);
            // load rows from source !
            newValuesFromSource = sourceDAO.findAllSinceLastTargetActivity(lastRow, valueList, tableName);
        }
        // save rows from source to target !
        targetDAO.saveAll(newValuesFromSource, typeList, valueList, tableName);
        System.out.println("success");
    }
}
