package psychotest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import psychotest.entity.EntitySbertest;
import java.util.*;

@Repository
public class SourceRepository extends SbertestRepository{

    private static String sourceTableName;
    @Value("${datasource.two.name}")
    public void setSourceTableName(String sourceTableName) {
        this.sourceTableName = sourceTableName;
    }

    @Autowired
    @Qualifier("jdbcTemplate2")
    private JdbcTemplate jdbcTemplate;

    private String sqlSelect;
    private String sqlInsert;

    public List<EntitySbertest> getDataCall(){
        sqlSelect = "select * from "+ sourceTableName +"";
        return getData(sqlSelect, jdbcTemplate);
    }

    public void saveDataCall(List<EntitySbertest> employeeList){
        sqlInsert = "INSERT INTO "+ sourceTableName +"(id, extid_BCKGR, extid_USER, tabnum, change_DATE, extid_PROGRAM, name_PROGRAM, scale, end_DATE_SCORE, name_SCORE, start_DATE_SCORE, extid_TEST, name_TEST, result_SCORE_NUM) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
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

    public List<EntitySbertest> getDataSinceCurrentDate(Long lastTargetTime) {
        try {
            String sql = "select * from "+ sourceTableName +" where unix_timestamp(end_DATE_SCORE)*1000 > ?;";

            List<EntitySbertest> sberTest2s = new ArrayList<>();

            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, lastTargetTime);

            for (Map<String, Object> row : rows) {
                EntitySbertest entitySbertest = new EntitySbertest();

                entitySbertest.setId((Long) row.get("id"));
                entitySbertest.setExtidBckgr((String)row.get("extid_BCKGR"));
                entitySbertest.setExtidUser((String)row.get("extid_USER"));
                entitySbertest.setTabnum((String)row.get("tabnum"));
                entitySbertest.setChangeDate((String)row.get("change_DATE"));
                entitySbertest.setExtidProgram((String)row.get("extid_PROGRAM"));
                entitySbertest.setNameProgram((String)row.get("name_PROGRAM"));
                entitySbertest.setScale((String)row.get("scale"));
                entitySbertest.setEndDateScore((String)row.get("end_DATE_SCORE"));
                entitySbertest.setNameScore((String)row.get("name_SCORE"));
                entitySbertest.setStartDateScore((String)row.get("start_DATE_SCORE"));
                entitySbertest.setExtidTest((String)row.get("extid_TEST"));
                entitySbertest.setNameTest((String)row.get("name_TEST"));
                entitySbertest.setResultScoreNum((Double) row.get("result_SCORE_NUM"));

                sberTest2s.add(entitySbertest);
            }
            return sberTest2s;
        } catch (Exception e){
            return Collections.EMPTY_LIST;
        }
    }
}
