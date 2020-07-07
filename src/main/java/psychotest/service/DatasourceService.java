package psychotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychotest.entity.DatasourceEntity;
import psychotest.repository.MainRepository;

@Service
public class DatasourceService {

    private final MainRepository mainRepository;

    @Autowired
    public DatasourceService(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public void saveDatasource(DatasourceEntity datasourceEntity) {
        mainRepository.saveDatasourceTable(datasourceEntity);
    }

    public DatasourceEntity getDatasourceById(Integer id) {
        return mainRepository.getDatasourceById(id);
    }

    public String findLastDataSourceByUrl() {
        return mainRepository.findLastDataSourceByUrl();
    }
}
