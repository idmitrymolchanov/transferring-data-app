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
@Sql(scripts = "/scriptForTarget.sql", config = @SqlConfig(dataSource = "config"))
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TargetRepositoryTest {

    private static String targetTableName;
    @Value("${datasource.target.name}")
    public void setTargetTableName(String targetTableName) {
        this.targetTableName = targetTableName;
    }

    private static JdbcTemplate jdbcTemplate;
    private static EntitySbertest entitySbertest;
    private String sql;

    @Autowired
    @Qualifier("config")
    private DataSource dataSource;

    @Autowired
    TargetRepository targetRepository;

    @Before
    public void sqlScript(){
        DataSourceTransactionManager txManager = new DataSourceTransactionManager(dataSource);
        jdbcTemplate = new JdbcTemplate(dataSource);
        TransactionStatus transaction = txManager.getTransaction(new DefaultTransactionDefinition());
        txManager.commit(transaction);
    }

    @Test
    public void getLastDate_testGetDate_dateReturned(){
        sql = "select max(cast(end_DATE_SCORE as date)) from " + targetTableName + "";
        LocalDate localDate = LocalDate.parse("2019-01-26");

        assertThat(targetRepository.getLastDate(sql, jdbcTemplate), Matchers.is(localDate));
    }

    @Test
    public void findById_canRetrieveByIdWhenExists_ListByIdReturned(){
        sql = "SELECT * from " + targetTableName + " where id = ?";
        List<EntitySbertest> list = targetRepository.findById(sql, jdbcTemplate, Long.valueOf(1243580));

        assertThat(list.get(0).getExtidBckgr(), Matchers.is("2168779357"));
    }

    @Test
    public void findById_canRetrieveByIdWhenDoesNotExist_emptyListReturned(){
        sql = "SELECT * from " + targetTableName + " where id = ?";
        List<EntitySbertest> list = targetRepository.findById(sql, jdbcTemplate, Long.valueOf(1243570));

        assertThat(list.size(), Matchers.is(0));
    }

    @Test
    public void save_canCreateANewEntry_ListWithNewEntryReturned(){
        sql = "INSERT INTO " + targetTableName + "(id, extid_BCKGR, extid_USER, tabnum, change_DATE, extid_PROGRAM, name_PROGRAM, scale, end_DATE_SCORE, name_SCORE, start_DATE_SCORE, extid_TEST, name_TEST, result_SCORE_NUM) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        entitySbertest = EntitySbertest
                .builder()
                .id(Long.parseLong("1243593"))
                .extidBckgr("2168779359")
                .extidUser("154708")
                .tabnum("1497935")
                .changeDate("2019-01-25 08:21:32.0000000")
                .extidProgram("personal-char")
                .nameProgram("value")
                .scale("0 - 10")
                .endDateScore("2019-01-25 00:00:00.0000000")
                .nameScore("value")
                .startDateScore("2019-01-21 00:00:00.0000000")
                .extidTest("27f18987-bf6d-4d08-8aec-d6f145cafOff")
                .nameTest("value")
                .resultScoreNum(1.0)
                .build();

        List<EntitySbertest> list = new ArrayList<>();
        list.add(entitySbertest);

        targetRepository.saveData(list, sql, jdbcTemplate);

        sql = "select * from " + targetTableName + " where id = ?;";
        List<EntitySbertest> resultList = targetRepository.findById(sql, jdbcTemplate, Long.valueOf(1243593));

        assertThat(resultList.get(0).getExtidBckgr(), Matchers.is("2168779359"));
    }

    @Test
    public void save_canCreateTwoEntriesWithTheSameValues_ListWithOneUniqEntryReturned(){
        sql = "INSERT INTO " + targetTableName + "(id, extid_BCKGR, extid_USER, tabnum, change_DATE, extid_PROGRAM, name_PROGRAM, scale, end_DATE_SCORE, name_SCORE, start_DATE_SCORE, extid_TEST, name_TEST, result_SCORE_NUM) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        List<EntitySbertest> list = new ArrayList<>();
        list.add(entitySbertest);
        targetRepository.saveData(list, sql, jdbcTemplate);

        sql = "select * from " + targetTableName + " where id = ?;";
        List<EntitySbertest> resultList = targetRepository.findById(sql, jdbcTemplate, Long.valueOf(1243593));

        assertThat(list.size(), Matchers.is(1));
    }

}
