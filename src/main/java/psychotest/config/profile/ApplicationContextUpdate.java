package psychotest.config.profile;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class ApplicationContextUpdate {
    private final ApplicationContext context;

    public ApplicationContextUpdate(ApplicationContext context) {
        this.context = context;
    }

    public void refreshCustomJdbc(String dataSourceBean, String jdbcTemplateBean) {
        DataSource ds = (DataSource) context.getBean(dataSourceBean);
        JdbcTemplate customJdbcTemplate = (JdbcTemplate) context.getBean(jdbcTemplateBean);
        customJdbcTemplate.setDataSource(ds);
    }
}
