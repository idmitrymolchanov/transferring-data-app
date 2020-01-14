package psychotest.config;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
public class DatabasesConfiguration {

    @Bean
    public TomcatEmbeddedServletContainerFactory tomcatFactory() {  // if datasouce defind in tomcat xml configuration then no need to create this bean

        return new TomcatEmbeddedServletContainerFactory() {

            protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(Tomcat tomcat) {
                tomcat.enableNaming();
                return super.getTomcatEmbeddedServletContainer(tomcat);
            }

            protected void postProcessContext(Context context) {
                ContextResource resource = new ContextResource();

                resource.setType(DataSource.class.getName());
                resource.setName("j4s");
                resource.setProperty("factory", "org.apache.tomcat.jdbc.pool.DataSourceFactory");
                resource.setProperty("driverClassName", "com.mysql.jdbc.Driver");
                resource.setProperty("url", "jdbc:mysql://localhost:3306/testdb");
                resource.setProperty("username", "root");
                resource.setProperty("password", "root");

                context.getNamingResources().addResource(resource);


                ContextResource resource2 = new ContextResource();

                resource2.setType(DataSource.class.getName());
                resource2.setName("j5s");
                resource2.setProperty("factory", "org.apache.tomcat.jdbc.pool.DataSourceFactory");
                resource2.setProperty("driverClassName", "com.mysql.jdbc.Driver");
                resource2.setProperty("url", "jdbc:mysql://localhost:3306/testdb2");
                resource2.setProperty("username", "root");
                resource2.setProperty("password", "root");

                context.getNamingResources().addResource(resource2);
            }
        };
    }

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
//URL: http://localhost:8080/springboot-jndi-lookup/get-cust-info