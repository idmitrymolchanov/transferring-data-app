package psychotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychotest.entity.HelpValueAndTypeEntity;
import psychotest.repository.BuildRepository;
import psychotest.repository.OtherSqlRepo;
import psychotest.repository.newRepo.NewSourceRepo;
import psychotest.repository.newRepo.NewTargetRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuiltService {

    private final OtherSqlRepo otherSqlRepo;
    private final BuildRepository buildRepository;
    private final NewTargetRepo newTargetRepo;
    private final NewSourceRepo newSourceRepo;

    @Autowired
    public BuiltService(OtherSqlRepo otherSqlRepo, BuildRepository buildRepository,
                        NewTargetRepo newTargetRepo, NewSourceRepo newSourceRepo) {
        this.otherSqlRepo = otherSqlRepo;
        this.buildRepository = buildRepository;
        this.newTargetRepo = newTargetRepo;
        this.newSourceRepo = newSourceRepo;
    }
/*
    public void twoListSeparate() {
        String tableName = "kk";
        List<HelpValueAndTypeEntity> entityList = buildRepository.getTypeAndValue(tableName); // забираем значения и типы полей
        List<String> valueList = new ArrayList<>(); // список значений
        List<String> typeList = new ArrayList<>(); // список полей
        for(int i = 0; i < entityList.size(); i++) {
            valueList.add(entityList.get(i).getStringValue());
            typeList.add(entityList.get(i).getStringType());
        }
        String uniqueString = buildRepository.getUniqueValue(tableName); // получаю уникальное поле
        String uniqueValue = otherSqlRepo.getLastValue(tableName, uniqueString); // значение уникального поля в реальной таблице
        int lastRow = otherSqlRepo.getNumberLastTargetString(tableName, uniqueValue, uniqueString, valueList);
        List<List<String>> newValuesFromTarget = otherSqlRepo.findAllSinceLastTargetActivity(lastRow, valueList, tableName);
        otherSqlRepo.saveAll(newValuesFromTarget, typeList);
    }*/

    public void twoListSeparate() {
        String tableName = "kk";
        List<HelpValueAndTypeEntity> entityList = buildRepository.getTypeAndValue(tableName); // забираем значения и типы полей
        List<String> valueList = new ArrayList<>(); // список значений
        List<String> typeList = new ArrayList<>(); // список полей
        for(int i = 0; i < entityList.size(); i++) {
            valueList.add(entityList.get(i).getStringValue());
            typeList.add(entityList.get(i).getStringType());
        }
        String uniqueString = buildRepository.getUniqueValue(tableName); // получаю уникальное поле
        String uniqueValue = otherSqlRepo.getLastValue(tableName, uniqueString); // значение уникального поля в реальной таблице
        int lastRow = otherSqlRepo.getNumberLastTargetString(tableName, uniqueValue, uniqueString, valueList);
        List<List<String>> newValuesFromTarget = newSourceRepo.findAllSinceLastTargetActivity(lastRow, valueList, tableName);
        newTargetRepo.saveAll(newValuesFromTarget, typeList, valueList, tableName);
    }

}
