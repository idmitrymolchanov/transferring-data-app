package psychotest.repository;

import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import psychotest.Config.TestConfig;
import psychotest.entity.EntitySbertest;

import javax.sql.DataSource;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@Import(TestConfig.class)
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SourceRepositoryTest {

    private static JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("config")
    private DataSource dataSource;

    @Autowired
    TargetRepository targetRepository;

    @Autowired
    SourceRepository sourceRepository;

    @Before
    public void sqlScript(){
        DataSourceTransactionManager txManager = new DataSourceTransactionManager(dataSource);
        jdbcTemplate = new JdbcTemplate(dataSource);
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

        jdbcTemplate.execute("DROP SCHEMA IF EXISTS testdb2");
        jdbcTemplate.execute("CREATE SCHEMA testdb2");
        jdbcTemplate.execute("DROP TABLE IF EXISTS testdb2.T_REPORT_SBERTEST_USER_PSYCHOTEST1");

        jdbcTemplate.execute("CREATE TABLE testdb2.T_REPORT_SBERTEST_USER_PSYCHOTEST1 (" +
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

        jdbcTemplate.execute("INSERT INTO testdb1.T_REPORT_SBERTEST_USER_PSYCHOTEST(id, extid_BCKGR, extid_USER, tabnum, change_DATE, extid_PROGRAM, name_PROGRAM, scale, end_DATE_SCORE, name_SCORE, start_DATE_SCORE, extid_TEST, name_TEST, result_SCORE_NUM) VALUES (1243580, '2168779357', '154708', '1497935', '2019-01-25 08:21:32.0000000', 'personal-char', 'valueNeRabotaet!', '0 - 10', '2019-01-25 00:00:00.0000000', 'value', '2019-01-21 00:00:00.0000000', '27f18987-bf6d-4d08-8aec-d6f145cafOff', 'value', 1);");
        jdbcTemplate.execute("INSERT INTO testdb2.T_REPORT_SBERTEST_USER_PSYCHOTEST1(id, extid_BCKGR, extid_USER, tabnum, change_DATE, extid_PROGRAM, name_PROGRAM, scale, end_DATE_SCORE, name_SCORE, start_DATE_SCORE, extid_TEST, name_TEST, result_SCORE_NUM) VALUES (1243581, '2168779358', '154708', '1497935', '2019-01-26 08:21:32.0000000', 'personal-char', 'valueNeRabotaet!', '0 - 10', '2019-01-26 00:00:00.0000000', 'value', '2019-01-22 00:00:00.0000000', '27f18987-bf6d-4d08-8aec-d6f145cafOff', 'value', 1);");
        txManager.commit(transaction);
    }

    @Test
    public void saveAllSinseLastTargetDateTest(){
        String sqlSelectDate = "select max(cast(end_DATE_SCORE as date)) from testdb1.T_REPORT_SBERTEST_USER_PSYCHOTEST";
        LocalDate localDate = targetRepository.getLastDateTest(sqlSelectDate, jdbcTemplate);
        String sqlSelectAllFromDate = "select * from testdb3.T_REPORT_SBERTEST_USER_PSYCHOTEST where cast(end_DATE_SCORE as date) > ?;";
        List<EntitySbertest> entitySbertestList = sourceRepository.findAllSinceLastTargetDate(localDate);
        assertThat(entitySbertestList.size(), Matchers.is(1));
        String sqlInsert = "INSERT INTO testdb1.T_REPORT_SBERTEST_USER_PSYCHOTEST(id, extid_BCKGR, extid_USER, tabnum, change_DATE, extid_PROGRAM, name_PROGRAM, scale, end_DATE_SCORE, name_SCORE, start_DATE_SCORE, extid_TEST, name_TEST, result_SCORE_NUM) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        targetRepository.saveData(entitySbertestList, sqlInsert, jdbcTemplate);
        String sqlSelectResult = "SELECT * from testdb1.T_REPORT_SBERTEST_USER_PSYCHOTEST where id = ?";
        Long id = Long.parseLong("1243581");
        List<EntitySbertest> list = targetRepository.findById(sqlSelectResult, jdbcTemplate, id);
        assertThat(list.get(0).getExtidBckgr(), Matchers.is("2168779358"));
    }
}
