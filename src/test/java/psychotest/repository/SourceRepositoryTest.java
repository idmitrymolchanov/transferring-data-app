package psychotest.repository;

import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import psychotest.config.TestConfig;
import psychotest.entity.EntitySbertest;

import javax.sql.DataSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@Import(TestConfig.class)
@Sql(scripts = "/scriptForSource.sql", config = @SqlConfig(dataSource = "config"))
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SourceRepositoryTest {

    private static String sourceTableName;
    @Value("${datasource.source.name}")
    public void setSourceTableName(String sourceTableName) {
        this.sourceTableName = sourceTableName;
    }

    private static JdbcTemplate jdbcTemplate;
    private static EntitySbertest entitySbertest;
    private String sql;

    @Autowired
    @Qualifier("config")
    private DataSource dataSource;

    @Autowired
    SourceRepository sourceRepository;

    @Before
    public void setJdbcTemplate(){
        DataSourceTransactionManager txManager = new DataSourceTransactionManager(dataSource);
        jdbcTemplate = new JdbcTemplate(dataSource);
        TransactionStatus transaction = txManager.getTransaction(new DefaultTransactionDefinition());
        txManager.commit(transaction);
    }

    @Test
    public void findAllSinceLastTargetDate_testCorrectFindEntry_ListWithCorrectEntryReturned(){
        LocalDate localDate = LocalDate.parse("2019-01-16");
        sql = "select * from " + sourceTableName + " where cast(end_DATE_SCORE as date) > ?;";
        List<EntitySbertest> list = sourceRepository.findAllSinceLastTargetDate(sql, jdbcTemplate, localDate);

        assertThat(list.size(), Matchers.is(1));
        assertThat(list.get(0).getId(), Matchers.is(Long.valueOf(1243583)));
    }

    @Test
    public void findById_canRetrieveByIdWhenExists_ListByIdReturned(){
        sql = "SELECT * from " + sourceTableName + " where id = ?";
        List<EntitySbertest> list = sourceRepository.findById(sql, jdbcTemplate, Long.valueOf(1243582));

        assertThat(list.get(0).getExtidBckgr(), Matchers.is("2168779356"));
    }

    @Test
    public void findById_canRetrieveByIdWhenDoesNotExist_emptyListReturned(){
        sql = "SELECT * from " + sourceTableName + " where id = ?";
        List<EntitySbertest> list = sourceRepository.findById(sql, jdbcTemplate, Long.valueOf(1243570));

        assertThat(list.size(), Matchers.is(0));
    }

    @Test
    public void save_canCreateANewEntry_ListWithNewEntryReturned(){
        sql = "INSERT INTO " + sourceTableName + "(id, extid_BCKGR, extid_USER, tabnum, change_DATE, extid_PROGRAM, name_PROGRAM, scale, end_DATE_SCORE, name_SCORE, start_DATE_SCORE, extid_TEST, name_TEST, result_SCORE_NUM) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        entitySbertest = EntitySbertest.builder().id(Long.parseLong("1243593")).extidBckgr("2168779359").extidUser("154708").tabnum("1497935").changeDate("2019-01-25 08:21:32.0000000").extidProgram("personal-char").nameProgram("value").scale("0 - 10").endDateScore("2019-01-25 00:00:00.0000000").nameScore("value").startDateScore("2019-01-21 00:00:00.0000000").extidTest("27f18987-bf6d-4d08-8aec-d6f145cafOff").nameTest("value").resultScoreNum(1.0).build();

        List<EntitySbertest> list = new ArrayList<>();
        list.add(entitySbertest);

        sourceRepository.saveAll(list, sql, jdbcTemplate);

        sql = "select * from " + sourceTableName + " where id = ?;";
        List<EntitySbertest> resultList = sourceRepository.findById(sql, jdbcTemplate, Long.valueOf(1243593));
        assertThat(resultList.get(0).getExtidBckgr(), Matchers.is("2168779359"));
    }

    @Test
    public void save_canCreateTwoEntriesWithTheSameValues_ListWithOneUniqEntryReturned(){
        sql = "INSERT INTO " + sourceTableName + "(id, extid_BCKGR, extid_USER, tabnum, change_DATE, extid_PROGRAM, name_PROGRAM, scale, end_DATE_SCORE, name_SCORE, start_DATE_SCORE, extid_TEST, name_TEST, result_SCORE_NUM) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        List<EntitySbertest> list = new ArrayList<>();
        list.add(entitySbertest);
        sourceRepository.saveAll(list, sql, jdbcTemplate);

        sql = "select * from " + sourceTableName + " where id = ?;";
        List<EntitySbertest> resultList = sourceRepository.findById(sql, jdbcTemplate, Long.valueOf(1243593));
        assertThat(list.size(), Matchers.is(1));
    }

}


