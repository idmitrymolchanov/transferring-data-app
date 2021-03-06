package psychotest.config.profile;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@Profile("local")
public class ConfigLocal {

    @Bean(name = "target")
    @ConfigurationProperties(prefix = "spring.target")
    public DataSource dataSourceTarget() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "jdbcTemplateTarget")
    public JdbcTemplate jdbcTemplateTarget(@Qualifier("target") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "source")
    @ConfigurationProperties(prefix = "spring.source")
    public DataSource dataSourceSource() {
        return  DataSourceBuilder.create().build();
    }

    @Bean(name = "jdbcTemplateSource")
    public JdbcTemplate jdbcTemplateSource(@Qualifier("source") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "userdb")
    @ConfigurationProperties(prefix = "spring.userdb")
    public DataSource dataSourceUser() {
        return  DataSourceBuilder.create().build();
    }

    @Bean(name = "jdbcTemplateUser")
    public JdbcTemplate jdbcTemplateUser(@Qualifier("userdb") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}