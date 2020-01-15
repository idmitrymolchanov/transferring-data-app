package psychotest.repository;

import com.google.common.collect.Lists;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import psychotest.entity.EntitySbertest;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class SbertestRepository {

    public List<EntitySbertest> getData(String SQL, JdbcTemplate jdbcTemplate){
        try {
            List<EntitySbertest> sberTest1s = new ArrayList<EntitySbertest>();

            List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL);

            for (Map<String, Object> row : rows) {
                EntitySbertest entitySbertest = new EntitySbertest();

                entitySbertest.setId((Long) row.get("id"));
                entitySbertest.setExtidBckgr((String) row.get("extid_BCKGR"));
                entitySbertest.setExtidUser((String) row.get("extid_USER"));
                entitySbertest.setTabnum((String) row.get("tabnum"));
                entitySbertest.setChangeDate((String) row.get("change_DATE"));
                entitySbertest.setExtidProgram((String) row.get("extid_PROGRAM"));
                entitySbertest.setNameProgram((String) row.get("name_PROGRAM"));
                entitySbertest.setScale((String) row.get("scale"));
                entitySbertest.setEndDateScore((String) row.get("end_DATE_SCORE"));
                entitySbertest.setNameScore((String) row.get("name_SCORE"));
                entitySbertest.setStartDateScore((String) row.get("start_DATE_SCORE"));
                entitySbertest.setExtidTest((String) row.get("extid_TEST"));
                entitySbertest.setNameTest((String) row.get("name_TEST"));
                entitySbertest.setResultScoreNum((Double) row.get("result_SCORE_NUM"));

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

}
