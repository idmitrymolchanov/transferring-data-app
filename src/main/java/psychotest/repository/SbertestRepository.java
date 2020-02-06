package psychotest.repository;

import com.google.common.collect.Lists;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import psychotest.entity.EntitySbertest;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class SbertestRepository {

    public List<EntitySbertest> findById(String SQL, JdbcTemplate jdbcTemplate, Long id){
        try {
            List<EntitySbertest> sberTest1s = new ArrayList<EntitySbertest>();

            List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL, id);

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
                sberTest1s.add(entitySbertest);
            }
            return sberTest1s;
        } catch (Exception e){
            return Collections.emptyList();
        }
    }

    public void saveData(final List<EntitySbertest> employeeList, String SQL, JdbcTemplate jdbcTemplate){
        try {
            final int batchSize = 500;
            List<List<EntitySbertest>> batchLists = Lists.partition(employeeList, batchSize);
            for (List<EntitySbertest> batch : batchLists) {
                jdbcTemplate.batchUpdate(SQL, new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i)
                            throws SQLException {
                        EntitySbertest entitySbertest = batch.get(i);
                        ps.setLong(1, entitySbertest.getId());
                        ps.setString(2, entitySbertest.getExtidBckgr());
                        ps.setString(3, entitySbertest.getExtidUser());
                        ps.setString(4, entitySbertest.getTabnum());
                        ps.setString(5, entitySbertest.getChangeDate());
                        ps.setString(6, entitySbertest.getExtidProgram());
                        ps.setString(7, entitySbertest.getNameProgram());
                        ps.setString(8, entitySbertest.getScale());
                        ps.setString(9, entitySbertest.getEndDateScore());
                        ps.setString(10, entitySbertest.getNameScore());
                        ps.setString(11, entitySbertest.getStartDateScore());
                        ps.setString(12, entitySbertest.getExtidTest());
                        ps.setString(13, entitySbertest.getNameTest());
                        ps.setDouble(14, entitySbertest.getResultScoreNum());
                    }

                    @Override
                    public int getBatchSize() {
                        return batch.size();
                    }
                });
            }
        } catch (Exception e){
            return;
        }
    }

    public LocalDate getLastDate(String sql, JdbcTemplate jdbcTemplate){
        String lastData = jdbcTemplate.queryForObject(sql, new Object[]{}, String.class);
        return LocalDate.parse(lastData);
    }

    public List<EntitySbertest> findAllSinceLastTargetDate(String sql, JdbcTemplate jdbcTemplate, LocalDate lastTargetTime) {
        try {

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
