package psychotest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import psychotest.entity.EntitySbertest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@PropertySource("classpath:application.properties")
public class TargetRepository extends SbertestRepository {

    private static String targetTableName;

    @Value("${datasource.one.name}")
    public void setTargetTableName(String targetTableName) {
        this.targetTableName = targetTableName;
    }

    @Autowired
    @Qualifier("jdbcTemplateTarget")
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

    public LocalDate getLastDate(){
        String sql = "select max(cast(end_DATE_SCORE as date)) from "+ targetTableName +"";
        String lastData = jdbcTemplate.queryForObject(sql, new Object[]{}, String.class);
        System.out.println(LocalDate.parse(lastData));
        return LocalDate.parse(lastData);
    }

    public List<EntitySbertest> getAllById(Long id) {
        try {
            String sql = "select * from "+ targetTableName +" where id = ?;";

            List<EntitySbertest> sberTest2s = new ArrayList<>();
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, id);

            for (Map<String, Object> row : rows) {
                EntitySbertest entitySbertest = EntitySbertest
                        .builder()
                        .id((Long) row.get("id"))
                        .extidBckgr((String) row.get("extid_BCKGR"))
                        .extidUser((String) row.get("extid_USER"))
                        .tabnum((String) row.get("tabnum"))
                        .changeDate((String) row.get("change_DATE"))
                        .extidProgram((String) row.get("extid_PROGRAM"))
                        .nameProgram((String) row.get("name_PROGRAM"))
                        .scale((String) row.get("scale"))
                        .endDateScore((String) row.get("end_DATE_SCORE"))
                        .nameScore((String) row.get("name_SCORE"))
                        .startDateScore((String) row.get("start_DATE_SCORE"))
                        .extidTest((String) row.get("extid_TEST"))
                        .nameTest((String) row.get("name_TEST"))
                        .resultScoreNum((Double) row.get("result_SCORE_NUM"))
                        .build();

                sberTest2s.add(entitySbertest);
            }
            return sberTest2s;
        } catch (Exception e){
            return Collections.EMPTY_LIST;
        }
    }
}
