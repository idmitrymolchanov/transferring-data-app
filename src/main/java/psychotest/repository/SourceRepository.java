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

    private static String sourceTableName;
    @Value("${datasource.two.name}")
    public void setSourceTableName(String sourceTableName) {
        this.sourceTableName = sourceTableName;
    }

    @Autowired
    @Qualifier("jdbcTemplateSource")
    private JdbcTemplate jdbcTemplate;

    private String sqlSelect;
    private String sqlInsert;

    public void save(List<EntitySbertest> employeeList){
        sqlInsert = "INSERT INTO "+ sourceTableName +"(id, extid_BCKGR, extid_USER, tabnum, change_DATE, extid_PROGRAM, name_PROGRAM, scale, end_DATE_SCORE, name_SCORE, start_DATE_SCORE, extid_TEST, name_TEST, result_SCORE_NUM) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        super.saveAll(employeeList, sqlInsert, jdbcTemplate);
    }

    public List<EntitySbertest> findById(Long id){
        sqlSelect = "select * from "+ sourceTableName +" where id = ?;";
        return findById(sqlSelect, jdbcTemplate, id);
    }

    public List<EntitySbertest> findAllSinceLastTargetDate(LocalDate lastTargetTime) {
            String sql = "select * from "+ sourceTableName +" where cast(end_DATE_SCORE as date) > ?;";
            return findAllSinceLastTargetDate(sql, jdbcTemplate, lastTargetTime);
    }
}
