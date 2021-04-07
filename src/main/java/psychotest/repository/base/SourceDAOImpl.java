package psychotest.repository.base;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
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
import java.util.Map;

@Repository
public class SourceDAOImpl implements SourceDAO {
    private final JdbcTemplate jdbcTemplate;
    private final ApplicationContext context;

    @Autowired
    public SourceDAOImpl(@Qualifier("jdbcTemplateSource") JdbcTemplate jdbcTemplate, ApplicationContext context) {
        this.jdbcTemplate = jdbcTemplate;
        this.context = context;
    }

    @Override
    public void refreshCustomJdbc() {
        DataSource ds = (DataSource) context.getBean("source");
        JdbcTemplate customJdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplateSource");
        customJdbcTemplate.setDataSource(ds);
    }

    @Override
    public List<List<String>> findAllSinceLastTargetActivity(int lastRow, List<String> values, String tableName) {
        String sql = "SELECT * FROM "+ tableName +" LIMIT "+ lastRow +", 100;";
        try {
            List<List<String>> lists = new ArrayList<>();
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

            for (Map<String, Object> row : rows) {
                List<String> valueList = new ArrayList<>();
                for(int i = 0; i < values.size(); i++)
                    valueList.add(row.get(values.get(i)).toString());
                lists.add(valueList);
            }
            return lists;
        } catch (Exception e){
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public Integer getNumberLastString(String tableName, String uniqueValue, String uniqueString, List<String> list){
        String as = "";
        for(int i = 0; i < list.size(); i ++) {
            as += (list.get(i) + ", ");
        }
        as = StringUtils.chop(as);
        as = StringUtils.chop(as);
        System.out.println(as);
    /*    String sql = "SELECT row_number() OVER (ORDER BY "+ uniqueString +") as "+ as +" \n" +
                " FROM "+ tableName +" \n" +
                " WHERE "+ uniqueString +"="+uniqueValue+";";*/

     /*   jdbcTemplate.execute("SET @i = 0;");
        String sql = "SELECT r FROM (" +
                "SELECT @i := @i + 1 AS r, id as d " +
                "FROM "+tableName+" as z ORDER BY id ASC) as m WHERE d=15;";*/

        String sql = "SELECT id FROM "+ tableName +" WHERE "+ uniqueString +" = "+ uniqueValue +"";

        //       String sql = "SELECT row_number() FROM "+ tableName +" ORDER BY "+ uniqueString +" DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new Object[]{}, Integer.class);
    }

    @Override
    public List<String> getTablesNames() {
        Connection connection = getConnection();
        List<String> tablesNames = new ArrayList<>();

        return getStrings(connection, tablesNames);
    }

    static List<String> getStrings(Connection connection, List<String> tablesNames) {
        DatabaseMetaData metaData;
        try {
            metaData = connection.getMetaData();
            String[] types = {"TABLE"};
            metaData.getConnection();
            //Retrieving the columns in the database
            ResultSet tables = metaData.getTables(connection.getCatalog(), connection.getSchema(), "%", types);
            while (tables.next())
                tablesNames.add(tables.getString("TABLE_NAME"));
        } catch (SQLException ignored)
        {
            return Collections.EMPTY_LIST;
        }

        return tablesNames;
    }

    @Override
    public List<String> getColumnsNames(String tableName) {
        Connection connection = getConnection();
        DatabaseMetaData metaData;
        List<String> tablesNames = new ArrayList<>();

        try {
            metaData = connection.getMetaData();
            metaData.getConnection();
            //Retrieving the columns in the database
            ResultSet tables = metaData.getColumns(connection.getCatalog(), connection.getSchema(),
                    tableName, "%");
            while (tables.next())
                tablesNames.add(tables.getString("COLUMN_NAME"));
        } catch (SQLException ignored)
        {
            return Collections.EMPTY_LIST;
        }

        return tablesNames;
    }

    @Override
    public List<String> getColumnsTypes(String tableName) {
        Connection connection = getConnection();
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

        System.out.println(columnTypes);
        return columnTypes;
    }

    private Connection getConnection() {
        DataSource dataSource = jdbcTemplate.getDataSource();
        assert dataSource != null;
        return DataSourceUtils.getConnection(dataSource);
    }
}