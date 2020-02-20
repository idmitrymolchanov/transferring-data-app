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

    private String targetTableName;
    @Value("${datasource.target.name}")
    public void setTargetTableName(String targetTableName) {
        this.targetTableName = targetTableName;
    }

    private static JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("config")
    private DataSource dataSource;

    @Autowired
    TargetRepository targetRepository;

    @Autowired
    RepositorySbertest repositorySbertest;

    @Before
    public void sqlScript(){
        DataSourceTransactionManager txManager = new DataSourceTransactionManager(dataSource);
        jdbcTemplate = new JdbcTemplate(dataSource);
        TransactionStatus transaction = txManager.getTransaction(new DefaultTransactionDefinition());
        txManager.commit(transaction);
    }

    @Test
    public void getLastDate_testGetDate_dateReturned(){
        LocalDate localDate = LocalDate.parse("2019-01-26");
        assertThat(targetRepository.getLastDate(targetTableName, jdbcTemplate), Matchers.is(localDate));
    }

    @Test
    public void findById_canRetrieveByIdWhenExists_objectByIdReturned(){
        EntitySbertest entitySbertest = repositorySbertest.findById(targetTableName, jdbcTemplate, Long.valueOf(1243580));
        assertThat(entitySbertest.getExtidBckgr(), Matchers.is("2168779357"));
    }

    @Test
    public void findById_canRetrieveByIdWhenDoesNotExist_nullValueReturned(){
        EntitySbertest entitySbertest = repositorySbertest.findById(targetTableName, jdbcTemplate, Long.valueOf(1243570));
        assertThat(entitySbertest, Matchers.is(Matchers.nullValue()));
    }

    @Test
    public void save_canCreateANewEntry_ListWithNewEntryReturned(){
         EntitySbertest entitySbertest = EntitySbertest
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
        targetRepository.saveAll(list, targetTableName, jdbcTemplate);

        EntitySbertest result = repositorySbertest.findById(targetTableName, jdbcTemplate, Long.valueOf(1243593));
        assertThat(result.getExtidBckgr(), Matchers.is("2168779359"));
    }

    @Test
    public void save_canCreateTwoEntriesWithTheSameValues_oneUniqEntryReturned(){
        EntitySbertest entitySbertest = EntitySbertest
                .builder().id(Long.parseLong("1243593")).extidBckgr("2168779359").extidUser("154708").tabnum("1497935").changeDate("2019-01-25 08:21:32.0000000").extidProgram("personal-char").nameProgram("value").scale("0 - 10").endDateScore("2019-01-25 00:00:00.0000000").nameScore("value").startDateScore("2019-01-21 00:00:00.0000000").extidTest("27f18987-bf6d-4d08-8aec-d6f145cafOff").nameTest("value").resultScoreNum(1.0).build();
        EntitySbertest entitySbertest2 = EntitySbertest
                .builder().id(Long.parseLong("1243593")).extidBckgr("2168779359").extidUser("154708").tabnum("1497935").changeDate("2019-01-25 08:21:32.0000000").extidProgram("personal-char").nameProgram("value").scale("0 - 10").endDateScore("2019-01-25 00:00:00.0000000").nameScore("value").startDateScore("2019-01-21 00:00:00.0000000").extidTest("27f18987-bf6d-4d08-8aec-d6f145cafOff").nameTest("value").resultScoreNum(1.0).build();

        List<EntitySbertest> list = new ArrayList<>();
        list.add(entitySbertest);
        list.add(entitySbertest2);
        targetRepository.saveAll(list, targetTableName, jdbcTemplate);

        EntitySbertest result = repositorySbertest.findById(targetTableName, jdbcTemplate, Long.valueOf(1243593));
        assertThat(result.getId(), Matchers.is(Long.valueOf(1243593)));
    }

}
