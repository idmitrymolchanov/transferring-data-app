package psychotest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import psychotest.entity.EntitySbertest;

import java.time.LocalDate;
import java.util.List;

@Repository
@PropertySource("classpath:application.properties")
public class TargetRepository extends SbertestRepository {

    private static String targetTableName;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TargetRepository(@Qualifier("jdbcTemplateTarget") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${datasource.one.name}")
    public void setTargetTableName(String targetTableName) {
        this.targetTableName = targetTableName;
    }

    private String sqlSelect;
    private String sqlInsert;

    public void save(List<EntitySbertest> employeeList){
        sqlInsert = "INSERT INTO "+ targetTableName +"(id, extid_BCKGR, extid_USER, tabnum, change_DATE, extid_PROGRAM, name_PROGRAM, scale, end_DATE_SCORE, name_SCORE, start_DATE_SCORE, extid_TEST, name_TEST, result_SCORE_NUM) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        super.saveAll(employeeList, sqlInsert, jdbcTemplate);
    }

    public List<EntitySbertest> findById(Long id){
        sqlSelect = "select * from "+ targetTableName +" where id = ?;";
        return findById(sqlSelect, jdbcTemplate, id);
    }

    public LocalDate getLastDate(){
        String sql = "select max(cast(end_DATE_SCORE as date)) from "+ targetTableName +"";
        return getLastDate(sql, jdbcTemplate);
    }

}
