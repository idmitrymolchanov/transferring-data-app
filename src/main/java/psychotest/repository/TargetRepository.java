package psychotest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import psychotest.entity.EntitySbertest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
@PropertySource("classpath:application.properties")
public class TargetRepository extends SbertestRepository {

    private static String targetTableName;

    @Value("${datasource.one.name}")
    public void setTargetTableName(String targetTableName) {
        this.targetTableName = targetTableName;
    }

    @Autowired
    @Qualifier("jdbcTemplate1")
    private JdbcTemplate jdbcTemplate;

    private String sqlSelect;
    private String sqlInsert;

    public List<EntitySbertest> getDataCall(){
        sqlSelect = "select * from "+ targetTableName +"";
        return getData(sqlSelect, jdbcTemplate);
    }

    public void saveDataCall(List<EntitySbertest> employeeList){
        sqlInsert = "INSERT INTO "+ targetTableName +"(id, extid_BCKGR, extid_USER, tabnum, change_DATE, extid_PROGRAM, name_PROGRAM, scale, end_DATE_SCORE, name_SCORE, start_DATE_SCORE, extid_TEST, name_TEST, result_SCORE_NUM) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        saveData(employeeList, sqlInsert, jdbcTemplate);
    }

    @Override
    public List<EntitySbertest> getData(String SQL, JdbcTemplate jdbcTemplate) {
        return super.getData(SQL, jdbcTemplate);
    }

    @Override
    public void saveData(List<EntitySbertest> employeeList, String SQL, JdbcTemplate jdbcTemplate) {
        super.saveData(employeeList, SQL, jdbcTemplate);
    }

    public Long getLastDate(){
        try {
            String sql = "select max(cast(end_DATE_SCORE as date)) from "+ targetTableName +"";
            String lastData = jdbcTemplate.queryForObject(sql, new Object[]{}, String.class);
            return new SimpleDateFormat("yyyy-MM-dd").parse(lastData).getTime();
        } catch (ParseException e){
            return null;
        }
    }
}
