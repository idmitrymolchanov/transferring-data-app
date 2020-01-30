package psychotest;

import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import psychotest.entity.EntitySbertest;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class DatabaseConTest {

    private static DataSource dataSource;

    @BeforeClass
    public static void init() throws InterruptedException{
        Thread.sleep(5_000); //wait for the docker container to start
        String port = System.getProperty("mysql.port", "3306");
        String pw = System.getProperty("mysql.pw", "root");
        String url = "jdbc:mysql://localhost:" + port + "?useUnicode=true&serverTimezone=UTC";
        log.info("MySQL URL: " + url +" with pw " + pw);
        dataSource = DataSourceBuilder.create()
                .url(url)
                .username("root")
                .password(pw)
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    @Test
    public void createData() throws Exception {
        DataSourceTransactionManager txManager = new DataSourceTransactionManager(dataSource);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        TransactionStatus transaction = txManager.getTransaction(new DefaultTransactionDefinition());
        jdbcTemplate.execute("DROP SCHEMA IF EXISTS testdb1");
        jdbcTemplate.execute("CREATE SCHEMA testdb1");
        jdbcTemplate.execute("DROP TABLE IF EXISTS testdb1.T_REPORT_SBERTEST_USER_PSYCHOTEST");

        jdbcTemplate.execute("CREATE TABLE testdb1.T_REPORT_SBERTEST_USER_PSYCHOTEST (" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT,"+
                "extid_BCKGR VARCHAR(255),"+
                "extid_USER VARCHAR(255),"+
                "tabnum VARCHAR(255),"+
                "change_DATE VARCHAR(255),"+
                "extid_PROGRAM VARCHAR(255),"+
                "name_PROGRAM VARCHAR(255),"+
                "scale VARCHAR(255),"+
                "end_DATE_SCORE VARCHAR(255),"+
                "name_SCORE VARCHAR(255),"+
                "start_DATE_SCORE VARCHAR(255),"+
                "extid_TEST VARCHAR(255),"+
                "name_TEST VARCHAR(255),"+
                "result_SCORE_NUM DOUBLE"+
                ");");

        jdbcTemplate.execute("INSERT INTO testdb1.T_REPORT_SBERTEST_USER_PSYCHOTEST(id, extid_BCKGR, extid_USER, tabnum, change_DATE, extid_PROGRAM, name_PROGRAM, scale, end_DATE_SCORE, name_SCORE, start_DATE_SCORE, extid_TEST, name_TEST, result_SCORE_NUM) VALUES (1243550, '2168779357', '154708', '1497935', '2019-01-25 08:21:32.0000000', 'personal-char', 'valueNeRabotaet!', '0 - 10', '2019-01-25 00:00:00.0000000', 'value', '2019-01-21 00:00:00.0000000', '27f18987-bf6d-4d08-8aec-d6f145cafOff', 'value', 1);");
        jdbcTemplate.execute("INSERT INTO testdb1.T_REPORT_SBERTEST_USER_PSYCHOTEST(id, extid_BCKGR, extid_USER, tabnum, change_DATE, extid_PROGRAM, name_PROGRAM, scale, end_DATE_SCORE, name_SCORE, start_DATE_SCORE, extid_TEST, name_TEST, result_SCORE_NUM) VALUES (1243551, '2168779357', '154708', '1497935', '2019-01-26 08:21:32.0000000', 'personal-char', 'valueNeRabotaet!', '0 - 10', '2019-01-26 00:00:00.0000000', 'value', '2019-01-22 00:00:00.0000000', '27f18987-bf6d-4d08-8aec-d6f145cafOff', 'value', 1);");
        jdbcTemplate.execute("INSERT INTO testdb1.T_REPORT_SBERTEST_USER_PSYCHOTEST(id, extid_BCKGR, extid_USER, tabnum, change_DATE, extid_PROGRAM, name_PROGRAM, scale, end_DATE_SCORE, name_SCORE, start_DATE_SCORE, extid_TEST, name_TEST, result_SCORE_NUM) VALUES (1243552, '2168779357', '154708', '1497935', '2019-01-27 08:21:32.0000000', 'personal-char', 'valueNeRabotaet!', '0 - 10', '2019-01-27 00:00:00.0000000', 'value', '2019-01-23 00:00:00.0000000', '27f18987-bf6d-4d08-8aec-d6f145cafOff', 'value', 1);");
        jdbcTemplate.execute("INSERT INTO testdb1.T_REPORT_SBERTEST_USER_PSYCHOTEST(id, extid_BCKGR, extid_USER, tabnum, change_DATE, extid_PROGRAM, name_PROGRAM, scale, end_DATE_SCORE, name_SCORE, start_DATE_SCORE, extid_TEST, name_TEST, result_SCORE_NUM) VALUES (1243553, '2168779357', '154708', '1497935', '2019-01-28 08:21:32.0000000', 'personal-char', 'valueNeRabotaet!', '0 - 10', '2019-01-28 00:00:00.0000000', 'value', '2019-01-24 00:00:00.0000000', '27f18987-bf6d-4d08-8aec-d6f145cafOff', 'value', 1);");

        String sql = "SELECT * from testdb1.T_REPORT_SBERTEST_USER_PSYCHOTEST where id = ?";
        Long id = Long.parseLong("1243550");
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, id);
        List<EntitySbertest> list = new ArrayList<>();

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

            list.add(entitySbertest);
        }

        if(list.get(0).getExtidBckgr().equals("2168779357")){
            log.info("values are equal");
        } else throw new Exception();

        txManager.commit(transaction);
    }
}