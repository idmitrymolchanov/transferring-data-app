package psychotest.repository.base;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import psychotest.parser.TypesParser;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class TargetDAOImpl implements TargetDAO {
    private final JdbcTemplate jdbcTemplate;
    private final ApplicationContext context;

    @Autowired
    public TargetDAOImpl(@Qualifier("jdbcTemplateTarget") JdbcTemplate jdbcTemplate, ApplicationContext context) {
        this.jdbcTemplate = jdbcTemplate;
        this.context = context;
    }

    @Override
    public void refreshCustomJdbc() {
        DataSource ds = (DataSource) context.getBean("target");
        JdbcTemplate customJdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplateTarget");
        customJdbcTemplate.setDataSource(ds);
    }

    @Override
    public void saveAll(final List<List<String>> employeeList, List<String> types, List<String> fields, String tableName){
        String as = "";
        String p = "";
        for(int i = 0; i < fields.size(); i ++) {
            as += (fields.get(i) + ",");
            p += "?,";
        }
        as = StringUtils.chop(as);
        p = StringUtils.chop(p);
        String sql = "INSERT INTO "+ tableName +"("+ as +") VALUES("+ p +");";

        try {
            TypesParser typesParser = new TypesParser();
            final int batchSize = 500;
            List<List<List<String>>> batchLists = Lists.partition(employeeList, batchSize);
            for (List<List<String>> batch : batchLists) {
                jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i)
                            throws SQLException {
                        List<String> valueList = batch.get(i);
                        for(int j = 0; j < valueList.size(); j++) {
                            switch (typesParser.getCodeByValue(types.get(j))) {
                                case 1:
                                    ps.setString(j+1, valueList.get(j));
                                    break;
                                case 2:
                                    ps.setInt(j+1, Integer.parseInt(valueList.get(j)));
                                    break;
                                case 3:
                                    ps.setBoolean(j+1, Boolean.parseBoolean(valueList.get(j)));
                                    break;
                                case 4:
                                    ps.setByte(j+1, Byte.parseByte(valueList.get(j)));
                                    break;
                                case 5:
                                    ps.setDate(j+1, Date.valueOf(valueList.get(j)));
                                    break;
                                case 6:
                                    ps.setDouble(j+1, Double.parseDouble(valueList.get(j)));
                                    break;
                                case 7:
                                    ps.setFloat(j+1, Float.parseFloat(valueList.get(j)));
                                    break;
                                case 8:
                                    ps.setLong(j+1, Long.parseLong(valueList.get(j)));
                                    break;
                                case 9:
                                    ps.setTime(j+1, Time.valueOf(valueList.get(j)));
                                    break;
                            }
                        }
                    }

                    @Override
                    public int getBatchSize() {
                        return batch.size();
                    }
                });
            }
        } catch (Exception e) {
            log.error("can not to save data in target");
            return;
        }
    }

    @Override
    public String getLastValue(String tableName, String uniqueValue) {
        try {
            String sql = "SELECT " + uniqueValue + " FROM " + tableName + " ORDER BY " + uniqueValue + " DESC LIMIT 1;";
            String lastValue = jdbcTemplate.queryForObject(sql, new Object[]{}, String.class);
            return lastValue;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<String> getValuesOfLastRow(String tableName, List<String> valueList) {
        try {
            String sql = "SELECT * FROM " + tableName + " WHERE id=(SELECT max(id) FROM " + tableName + ");";

            List<String> list = new ArrayList<>();
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
            for (Map<String, Object> row : rows) {
                for (int i = 0; i < valueList.size(); i++)
                    list.add(row.get(valueList.get(i)).toString());
            }
            return list;
        } catch (Exception ignored) {
            log.error("can not get values of last target row");
            return Collections.EMPTY_LIST;
        }
    }

    private Connection getConnection() {
        DataSource dataSource = jdbcTemplate.getDataSource();
        assert dataSource != null;
        return DataSourceUtils.getConnection(dataSource);
    }

    private String getTargetDialect() {
        try (Connection connection = getConnection()) {
            return connection.getMetaData().getDriverVersion();
        } catch (Exception e){
            return null;
        }
    }
}
