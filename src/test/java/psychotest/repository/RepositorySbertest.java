package psychotest.repository;

import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import psychotest.config.TestConfig;
import psychotest.entity.EntitySbertest;

@Import(TestConfig.class)
@Repository
public class RepositorySbertest {

    public EntitySbertest findById(String tableName, JdbcTemplate jdbcTemplate, Long id) {

        try {
            String sql = "select id, extid_BCKGR, extid_USER, tabnum, change_DATE, extid_PROGRAM, name_PROGRAM, scale, end_DATE_SCORE, name_SCORE, start_DATE_SCORE, extid_TEST, name_TEST, result_SCORE_NUM from " + tableName + " where id = ?;";

            return jdbcTemplate.queryForObject(sql, (resultSet, i) -> EntitySbertest
                    .builder()
                    .id(resultSet.getLong("id"))
                    .extidBckgr(resultSet.getString("extid_BCKGR"))
                    .extidUser(resultSet.getString("extid_USER"))
                    .tabnum(resultSet.getString("tabnum"))
                    .changeDate(resultSet.getString("change_DATE"))
                    .extidProgram(resultSet.getString("extid_PROGRAM"))
                    .nameProgram(resultSet.getString("name_PROGRAM"))
                    .scale(resultSet.getString("scale"))
                    .endDateScore(resultSet.getString("end_DATE_SCORE"))
                    .nameScore(resultSet.getString("name_SCORE"))
                    .startDateScore(resultSet.getString("start_DATE_SCORE"))
                    .extidTest(resultSet.getString("extid_TEST"))
                    .nameTest(resultSet.getString("name_TEST"))
                    .resultScoreNum(resultSet.getDouble("result_SCORE_NUM"))
                    .build(), id);
        } catch (Exception e){
            return null;
        }
    }
}
