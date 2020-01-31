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
        saveData(employeeList, sqlInsert, jdbcTemplate);
    }

    public List<EntitySbertest> findById(Long id){
        sqlSelect = "select * from "+ sourceTableName +" where id = ?;";
        return findById(sqlSelect, jdbcTemplate, id);
    }

    @Override
    public void saveData(List<EntitySbertest> employeeList, String SQL, JdbcTemplate jdbcTemplate) {
        super.saveData(employeeList, SQL, jdbcTemplate);
    }

    public List<EntitySbertest> findAllSinceLastTargetDate(LocalDate lastTargetTime) {
        try {
            String sql = "select * from "+ sourceTableName +" where cast(end_DATE_SCORE as date) > ?;";

            List<EntitySbertest> sberTest2s = new ArrayList<>();
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, lastTargetTime.plusDays(1));

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
