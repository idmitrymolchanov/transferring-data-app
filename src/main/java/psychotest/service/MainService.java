package psychotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychotest.entity.EntityClass;
import psychotest.entity.MainEntity;
import psychotest.repository.MainRepository;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

@Service
public class MainService {

    private final MainRepository mainRepository;

    @Autowired
    public MainService(MainRepository superPuperRepo) {
        this.mainRepository = superPuperRepo;
    }

    public void createTable(String tableName) {
        String s;
    }

    public ArrayList entityList(ArrayList list) {
        return list;
    }

    public MainEntity mainEntityReturn() {
        return mainRepository.findByIdEntity(Long.valueOf(5));
    }

    public MainEntity mainEntityReturnByLast() {
        return mainRepository.findByLastIdEntity();
    }

    public void sortByTwoList(MainEntity mainEntity) {
        List<String> mainEntityValue = new ArrayList<>();
        List<String> mainEntityTypes = new ArrayList<>();
        try {
            Class<? extends MainEntity> mainEntityClass = mainEntity.getClass();
            for (Field field : mainEntityClass.getDeclaredFields()) {
                if (field.getModifiers() == Modifier.PRIVATE && field.getName().contains("value")) {
                    field.setAccessible(true);
                    mainEntityValue.add(field.get(mainEntity).toString());
                } else if(field.getModifiers() == Modifier.PRIVATE && field.getName().contains("type")) {
                    field.setAccessible(true);
                    mainEntityTypes.add(field.get(mainEntity).toString());
                }
            }
        } catch (Exception e) {}

        mainRepository.insertProcessing(mainEntityValue, "toValue");
        mainRepository.insertProcessing(mainEntityTypes, "toType");
    }

    public void normalToList(MainEntity mainEntity) {
        List<MainEntity> mainEntityList = new ArrayList<>();
        try {
            List<Field> fields = new ArrayList<>();
            Class<? extends MainEntity> mainEntityClass = mainEntity.getClass();
            for (Field field : mainEntityClass.getDeclaredFields()) {
                if (field.getType() == String.class && field.getModifiers() == Modifier.PRIVATE) {
                    fields.add(field);
                    field.setAccessible(true);
                    String hoValue = field.get(mainEntity).toString();
                    System.out.println(hoValue);
                }
            }
        } catch (Exception e) {}
    }

    public void saveTableName() {
        EntityClass entityClass = new EntityClass();
        mainRepository.saveTableNameAll(entityClass);
    }

    public String getTableNaME() {
        EntityClass entityClass = mainRepository.getTableNameAll();
        return entityClass.getQuantity();
    }

    public Integer getQuantity() {
        return mainRepository.getQuantityTable();
    }
}
