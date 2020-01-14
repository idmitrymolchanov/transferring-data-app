package psychotest.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
@Profile(value = "prod")
public class DatabaseCloudConfiguration {

    @Bean(name = "jndiDataSource")
    @Primary
    public DataSource jndiDataSource() throws IllegalArgumentException, NamingException {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName("java:/comp/env/j4s");
        bean.setProxyInterface(DataSource.class);
        bean.setLookupOnStartup(false);
        bean.afterPropertiesSet();

        return (DataSource) bean.getObject();
    }

    @Bean(name = "jndiDataSource2")
    public DataSource jndiDataSource2() throws IllegalArgumentException, NamingException {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName("java:/comp/env/j5s");
        bean.setProxyInterface(DataSource.class);
        bean.setLookupOnStartup(false);
        bean.afterPropertiesSet();

        return (DataSource) bean.getObject();
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
