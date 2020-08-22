package psychotest.config.profile;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;
import psychotest.entity.DatasourceEntity;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.List;

public class TomcatConfig {
    private final DatasourceEntity datasourceEntitySource;
    private final DatasourceEntity datasourceEntityTarget;

    public TomcatConfig(List<DatasourceEntity> list) {
        this.datasourceEntityTarget = list.get(0);
        this.datasourceEntitySource = list.get(1);
    }

    @Bean
    public TomcatServletWebServerFactory tomcatFactory() {
        return new TomcatServletWebServerFactory() {

            @Override
            protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
                tomcat.enableNaming();
                return super.getTomcatWebServer(tomcat);
            }

            @Override
            protected void postProcessContext(Context context) {
                //target
                ContextResource resource = new ContextResource();

                resource.setType(DataSource.class.getName());
                resource.setName("target");
                resource.setProperty("factory", "org.apache.tomcat.jdbc.pool.DataSourceFactory");
                resource.setProperty("driverClassName", datasourceEntityTarget.getDriver_name());
                resource.setProperty("url", ""+datasourceEntityTarget.getUrl()+"?useUnicode=true&serverTimezone=UTC");
                resource.setProperty("username", datasourceEntityTarget.getUsername());
                resource.setProperty("password", datasourceEntityTarget.getPassword());

                context.getNamingResources().addResource(resource);

                //source
                ContextResource resource2 = new ContextResource();

                resource2.setType(DataSource.class.getName());
                resource2.setName("source");
                resource2.setProperty("factory", "org.apache.tomcat.jdbc.pool.DataSourceFactory");
                resource2.setProperty("driverClassName", datasourceEntitySource.getDriver_name());
                resource2.setProperty("url", ""+datasourceEntitySource.getUrl()+"?useUnicode=true&serverTimezone=UTC");
                resource2.setProperty("username", datasourceEntitySource.getUsername());
                resource2.setProperty("password", datasourceEntitySource.getPassword());

                context.getNamingResources().addResource(resource2);
            }
        };
    }

    @Bean(name = "new-target")
    public DataSource jndiDataSource() throws IllegalArgumentException, NamingException
    {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName("java:/comp/env/target");
        bean.setProxyInterface(DataSource.class);
        bean.setLookupOnStartup(false);
        bean.afterPropertiesSet();

        return (DataSource) bean.getObject();
    }

    @Bean(name = "new-jdbcTemplateTarget")
    public JdbcTemplate jdbcTemplateTarget(@Qualifier("new-target") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "new-source")
    public DataSource jndiDataSource1() throws IllegalArgumentException, NamingException
    {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName("java:/comp/env/source");
        bean.setProxyInterface(DataSource.class);
        bean.setLookupOnStartup(false);
        bean.afterPropertiesSet();

        return (DataSource) bean.getObject();
    }

    @Bean(name = "new-jdbcTemplateSource")
    public JdbcTemplate jdbcTemplateSource(@Qualifier("new-source") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
