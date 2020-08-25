package psychotest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Autowired
    public MainServiceImpl(MainRequestDAO mainRequestDAO,
                           TargetDAO targetDAO, SourceDAO sourceDAO) {
        this.mainRequestDAO = mainRequestDAO;
        this.targetDAO = targetDAO;
        this.sourceDAO = sourceDAO;
    }

    static final int i = 900000;
    @Override
  //  @Scheduled(fixedRate = i)
    public void twoListSeparate() {
        try {
            String tableName = "T_REPORT_SBERTEST_USER_PSYCHOTEST";
            List<ValueTypeEntity> entityList = mainRequestDAO.getTypeAndValue(tableName); // забираем значения и типы полей

            System.out.println("забираем значения и типы полей");
            List<String> valueList = new ArrayList<>(); // список значений
            List<String> typeList = new ArrayList<>(); // список полей
            for (int i = 0; i < entityList.size(); i++) {
                valueList.add(entityList.get(i).getStringValue());
                typeList.add(entityList.get(i).getStringType());
            }

            String uniqueString = mainRequestDAO.getUniqueValue(tableName); // получаю уникальное поле
            System.out.println("получаю уникальное поле");
            String uniqueValue = targetDAO.getLastValue(tableName, uniqueString); // значение уникального поля в реальной таблице
            System.out.println("значение уникального поля в реальной таблице");
            System.out.println(uniqueString + " = " + uniqueValue + " - значениеееееееее");
            int lastRow = sourceDAO.getNumberLastString(tableName, uniqueValue, uniqueString, valueList);
            System.out.println("last row");
            List<List<String>> newValuesFromTarget = sourceDAO.findAllSinceLastTargetActivity(lastRow, valueList, tableName);
            System.out.println("find all");
            targetDAO.saveAll(newValuesFromTarget, typeList, valueList, tableName);
            System.out.println("success");
        } catch (Exception e) {

        }
    }

}
