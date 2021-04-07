package psychotest.config.profile;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import psychotest.entity.DatasourceEntity;

@Configuration
@Profile("local")
public class ConfigLocal {
    public static DatasourceEntity datasourceEntitySource;
    public static DatasourceEntity datasourceEntityTarget;

    @Lazy
    @Bean(name = "target")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public DataSource dataSourceTarget() {
        DataSourceBuilder dsBuilder = DataSourceBuilder.create();
        try {
            dsBuilder.driverClassName(datasourceEntityTarget.getDriver_name());
            dsBuilder.url(datasourceEntityTarget.getUrl()+"?useUnicode=true&serverTimezone=UTC");
            dsBuilder.username(datasourceEntityTarget.getUsername());
            dsBuilder.password(datasourceEntityTarget.getPassword());
        } catch (NullPointerException ignored) {}
        return dsBuilder.build();
    }

    @Lazy
    @Bean(name = "jdbcTemplateTarget")
    public JdbcTemplate jdbcTemplateTarget(@Qualifier("target") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Lazy
    @Bean(name = "source")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public DataSource dataSourceSource() {

        DataSourceBuilder dsBuilder = DataSourceBuilder.create();
        try {
            dsBuilder.driverClassName(datasourceEntitySource.getDriver_name());
            dsBuilder.url(datasourceEntitySource.getUrl() + "?useUnicode=true&serverTimezone=UTC");
            dsBuilder.username(datasourceEntitySource.getUsername());
            dsBuilder.password(datasourceEntitySource.getPassword());
        } catch (NullPointerException ignored) {}

        return dsBuilder.build();
    }

    @Lazy
    @Bean(name = "jdbcTemplateSource")
    public JdbcTemplate jdbcTemplateSource(@Qualifier("source") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "userdb")
    @ConfigurationProperties(prefix = "spring.userdb")
    public DataSource dataSourceUser() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "jdbcTemplateUser")
    public JdbcTemplate jdbcTemplateUser(@Qualifier("userdb") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}