package psychotest.config.profile;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiTemplate;

import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
@Profile(value = "prod")
public class ConfigProd {

    @Value("${psychotest.jndi.datasource.target")
    private String jndiTarget;

    @Value("${psychotest.jndi.datasource.source")
    private String jndiSource;

    @Bean(name = "jndiDataSource")
    @Primary
    public DataSource jndiDataSource() throws IllegalArgumentException, NamingException {
        JndiTemplate jndiTemplate = new JndiTemplate();
        return (DataSource) jndiTemplate.lookup(jndiTarget);
    }

    @Bean(name = "jndiDataSource2")
    public DataSource jndiDataSource2() throws IllegalArgumentException, NamingException {
        JndiTemplate jndiTemplate = new JndiTemplate();
        return (DataSource) jndiTemplate.lookup(jndiSource);
    }

    @Bean
    @Qualifier("jdbcTemplateTarget")
    public JdbcTemplate jdbcTemplateTarget() throws IllegalArgumentException, NamingException {
        return new JdbcTemplate(jndiDataSource());
    }

    @Bean
    @Qualifier("jdbcTemplateSource")
    public JdbcTemplate jdbcTemplateSource() throws IllegalArgumentException, NamingException {
        return new JdbcTemplate(jndiDataSource2());
    }
}
