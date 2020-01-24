package psychotest.config;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Value("${psychotest.jndi.datasource.one}")
    private String jndiStringOne;

    @Value("${psychotest.jndi.datasource.two}")
    private String jndiStringTwo;

    @Bean(name = "jndiDataSource")
    @Primary
    public DataSource jndiDataSource() throws IllegalArgumentException, NamingException {
        JndiTemplate jndiTemplate = new JndiTemplate();
        return (DataSource) jndiTemplate.lookup(jndiStringOne);
    }

    @Bean(name = "jndiDataSource2")
    public DataSource jndiDataSource2() throws IllegalArgumentException, NamingException {
        JndiTemplate jndiTemplate = new JndiTemplate();
        return (DataSource) jndiTemplate.lookup(jndiStringTwo);
    }

    @Bean
    @Qualifier("mySqljdbcTemplate")
    public JdbcTemplate mySqljdbcTemplate() throws IllegalArgumentException, NamingException {
        return new JdbcTemplate(jndiDataSource());
    }

    @Bean
    @Qualifier("mySqljdbcTemplate2")
    public JdbcTemplate mySqljdbcTemplate2() throws IllegalArgumentException, NamingException {
        return new JdbcTemplate(jndiDataSource2());
    }
}
