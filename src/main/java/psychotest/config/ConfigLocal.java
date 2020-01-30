package psychotest.config;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

@Profile("local")
@Configuration
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
}