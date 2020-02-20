package psychotest.service;

import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
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
import psychotest.repository.RepositorySbertest;
import psychotest.repository.SourceRepository;
import psychotest.repository.TargetRepository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@Import(TestConfig.class)
@Sql(scripts = {"/scriptForSource.sql", "/scriptForTarget.sql"}, config = @SqlConfig(dataSource = "config"))
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SchedulerTest {

    private static String sourceTableName;
    @Value("${datasource.source.name}")
    public void setSourceTableName(String sourceTableName) {
        this.sourceTableName = sourceTableName;
    }

    private static String targetTableName;
    @Value("${datasource.target.name}")
    public void setTargetTableName(String targetTableName) {
        this.targetTableName = targetTableName;
    }

    @Autowired
    @Qualifier("config")
    private DataSource dataSource;

    @Autowired
    TargetRepository targetRepository;

    @Autowired
    SourceRepository sourceRepository;

    @Autowired
    RepositorySbertest repositorySbertest;

    @Test
    public void saveAllSinseLastTargetDateTest(){
        DataSourceTransactionManager txManager = new DataSourceTransactionManager(dataSource);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        TransactionStatus transaction = txManager.getTransaction(new DefaultTransactionDefinition());
        txManager.commit(transaction);

        LocalDate localDate = targetRepository.getLastDate(targetTableName, jdbcTemplate);
        List<EntitySbertest> entitySbertestList = sourceRepository.findAllSinceLastTargetDate(sourceTableName, jdbcTemplate, localDate);
        assertThat(entitySbertestList.size(), Matchers.is(1));

        targetRepository.saveAll(entitySbertestList, targetTableName, jdbcTemplate);
        EntitySbertest result = repositorySbertest.findById(targetTableName, jdbcTemplate, Long.valueOf(1243583));
        assertThat(result.getExtidBckgr(), Matchers.is("2168779357"));
    }
}