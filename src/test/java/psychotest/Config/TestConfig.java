package psychotest.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@TestConfiguration
public class TestConfig {

    @Bean(name = "config")
    public DataSource init() throws InterruptedException{
        Thread.sleep(5_000); //wait for the docker container to start
        String port = System.getProperty("mysql.port", "3306");
        String pw = System.getProperty("mysql.pw", "root");
        String url = "jdbc:mysql://localhost:" + port + "?useUnicode=true&serverTimezone=UTC";
        DataSource dataSource = DataSourceBuilder.create()
                .url(url)
                .username("root")
                .password(pw)
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
        return dataSource;
    }
}
