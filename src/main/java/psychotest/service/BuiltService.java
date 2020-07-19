package psychotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import psychotest.entity.HelpValueAndTypeEntity;
import psychotest.repository.BuildRepository;
import psychotest.repository.newRepo.NewSourceRepo;
import psychotest.repository.newRepo.NewTargetRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuiltService {

    private final BuildRepository buildRepository;
    private final NewTargetRepo newTargetRepo;
    private final NewSourceRepo newSourceRepo;

    @Autowired
    public BuiltService(BuildRepository buildRepository,
                        NewTargetRepo newTargetRepo, NewSourceRepo newSourceRepo) {
        this.buildRepository = buildRepository;
        this.newTargetRepo = newTargetRepo;
        this.newSourceRepo = newSourceRepo;
    }

    static final int i = 9000;
    @Scheduled(fixedRate = i)
    public void twoListSeparate() {
        try {
            String tableName = "T_REPORT_SBERTEST_USER_PSYCHOTEST";
            List<HelpValueAndTypeEntity> entityList = buildRepository.getTypeAndValue(tableName); // забираем значения и типы полей
            System.out.println("забираем значения и типы полей");
            List<String> valueList = new ArrayList<>(); // список значений
            List<String> typeList = new ArrayList<>(); // список полей
            for (int i = 0; i < entityList.size(); i++) {
                valueList.add(entityList.get(i).getStringValue());
                typeList.add(entityList.get(i).getStringType());
            }

            String uniqueString = buildRepository.getUniqueValue(tableName); // получаю уникальное поле
            System.out.println("получаю уникальное поле");
            String uniqueValue = newTargetRepo.getLastValue(tableName, uniqueString); // значение уникального поля в реальной таблице
            System.out.println("значение уникального поля в реальной таблице");
            System.out.println(uniqueString + " = " + uniqueValue + " - значениеееееееее");
            int lastRow = newSourceRepo.getNumberLastString(tableName, uniqueValue, uniqueString, valueList);
            System.out.println("last row");
            List<List<String>> newValuesFromTarget = newSourceRepo.findAllSinceLastTargetActivity(lastRow, valueList, tableName);
            System.out.println("find all");
            newTargetRepo.saveAll(newValuesFromTarget, typeList, valueList, tableName);
            System.out.println("success");
        } catch (Exception e) {

        }
    }

}
