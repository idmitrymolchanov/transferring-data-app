package psychotest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import psychotest.entity.EntitySbertest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class TargetRepository extends SbertestRepository {

    @Autowired
    @Qualifier("mySqljdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT = "select * from T_REPORT_SBERTEST_USER_PSYCHOTEST";
    private static final String SQL_INSERT = "INSERT INTO T_REPORT_SBERTEST_USER_PSYCHOTEST(id, extid_BCKGR, extid_USER, tabnum, change_DATE, extid_PROGRAM, name_PROGRAM, scale, end_DATE_SCORE, name_SCORE, start_DATE_SCORE, extid_TEST, name_TEST, result_SCORE_NUM) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

    public List<EntitySbertest> getDataCall(){
        System.out.println(getLastDate());
        return getData(SQL_SELECT, jdbcTemplate);
    }

    public void saveDataCall(List<EntitySbertest> employeeList){
        saveData(employeeList, SQL_INSERT, jdbcTemplate);
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
            String sql = "select max(cast(end_DATE_SCORE as date)) from T_REPORT_SBERTEST_USER_PSYCHOTEST";
            String lastData = jdbcTemplate.queryForObject(sql, new Object[]{}, String.class);
            return new SimpleDateFormat("yyyy-MM-dd").parse(lastData).getTime();
        } catch (ParseException e){
            return null;
        }
    }
}
