package psychotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import psychotest.entity.DatasourceEntity;
import psychotest.entity.MainEntity;
import psychotest.repository.MainRepository;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class DatasourceService {

    private final MainRepository mainRepository;

    @Autowired
    public DatasourceService(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }


    public void sortList(DatasourceEntity datasourceEntity) {
        List<String> datasourceProperty = new ArrayList<>();
        try {
            Class<? extends DatasourceEntity> datasourceEntityClass = datasourceEntity.getClass();
            for (Field field : datasourceEntityClass.getDeclaredFields()) {
                if (field.getModifiers() == Modifier.PRIVATE) {
                    field.setAccessible(true);
                    datasourceProperty.add(field.get(datasourceEntity).toString());
                }
            }
        } catch (Exception e) {}

        mainRepository.saveDatasource(datasourceProperty);
    }
}
