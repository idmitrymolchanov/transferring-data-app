package psychotest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import psychotest.entity.EntitySbertest;

import java.time.LocalDate;
import java.util.List;

@Repository
@PropertySource("classpath:application.properties")
public class TargetRepository extends SbertestRepository {

    private String targetTableName;
    @Value("${datasource.one.name}")
    public void setTargetTableName(String targetTableName) {
        this.targetTableName = targetTableName;
    }

    @Autowired
    @Qualifier("jdbcTemplateTarget")
    private JdbcTemplate jdbcTemplate;

    public void save(List<EntitySbertest> employeeList){
        super.saveAll(employeeList, targetTableName, jdbcTemplate);
    }

    public LocalDate getLastDate(){
        return getLastDate(targetTableName, jdbcTemplate);
    }

}
