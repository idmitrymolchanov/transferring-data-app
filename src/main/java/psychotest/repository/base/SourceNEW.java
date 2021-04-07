package psychotest.repository.base;

import com.google.errorprone.annotations.concurrent.LazyInit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static psychotest.repository.base.SourceDAOImpl.getStrings;

@Repository
public class SourceNEW {
    private final JdbcTemplate jdbcTemplate;
    private final ApplicationContext context;

    @Autowired
    public SourceNEW(@Qualifier("jdbcTemplateSource") JdbcTemplate jdbcTemplate, ApplicationContext context) {
        this.jdbcTemplate = jdbcTemplate;
        this.context = context;
    }


    public void refreshCustomJdbc() {
        DataSource ds = (DataSource) context.getBean("source");
        JdbcTemplate customJdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplateSource");
        customJdbcTemplate.setDataSource(ds);
    }


    public List<String> getNameTables1() {
        System.out.println("in source");

        refreshCustomJdbc();

        DataSource dataSource = jdbcTemplate.getDataSource();
      //  System.out.println(dataSource + "<- this is a datasource");
        Connection connection = DataSourceUtils.getConnection(dataSource);
        DatabaseMetaData metaData;
        List<String> tablesNames = new ArrayList<>();

        return getStrings(connection, tablesNames);
    }

    public List<String> getColumnsNames(String tableName) {
        DataSource dataSource = jdbcTemplate.getDataSource();
        Connection connection = DataSourceUtils.getConnection(dataSource);
        DatabaseMetaData metaData;
        List<String> tablesNames = new ArrayList<>();

        try {
            metaData = connection.getMetaData();
            String[] types = {"COLUMN_NAME"};
            metaData.getConnection();
            //Retrieving the columns in the database
            ResultSet tables = metaData.getColumns(connection.getCatalog(), connection.getSchema(),
                    tableName, "%");
            while (tables.next()) {
                tablesNames.add(tables.getString("COLUMN_NAME"));
            }
        } catch (SQLException ignored)
        {
            return Collections.EMPTY_LIST;
        }

        return tablesNames;
    }

    public List<String> getColumnsTypes(String tableName) {
        DataSource dataSource = jdbcTemplate.getDataSource();
        Connection connection = DataSourceUtils.getConnection(dataSource);
        DatabaseMetaData metaData;
        List<String> columnTypes = new ArrayList<>();
        try {
            metaData = connection.getMetaData();
            //Retrieving the columns in the database
            ResultSet tables = metaData.getColumns(connection.getCatalog(), connection.getSchema(),
                    tableName, "%");
            while (tables.next())
                columnTypes.add(tables.getString("TYPE_NAME"));
        } catch (SQLException ignored)
        {
            return Collections.EMPTY_LIST;
        }
        return columnTypes;
    }
}
