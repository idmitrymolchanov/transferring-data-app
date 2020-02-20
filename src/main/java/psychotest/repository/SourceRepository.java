package psychotest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import psychotest.entity.EntitySbertest;

import java.time.LocalDate;
import java.util.*;

@Repository
public class SourceRepository extends SbertestRepository{

    private String sourceTableName;
    @Value("${datasource.two.name}")
    public void setSourceTableName(String sourceTableName) {
        this.sourceTableName = sourceTableName;
    }

    @Autowired
    @Qualifier("jdbcTemplateSource")
    private JdbcTemplate jdbcTemplate;

    public void save(List<EntitySbertest> employeeList){
        super.saveAll(employeeList, sourceTableName, jdbcTemplate);
    }

    public List<EntitySbertest> findAllSinceLastTargetDate(LocalDate lastTargetTime) {
            return findAllSinceLastTargetDate(sourceTableName, jdbcTemplate, lastTargetTime);
    }
}
