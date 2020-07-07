package psychotest.repository;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import psychotest.entity.EntitySbertest;
import psychotest.parser.TypesParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class OtherSqlRepo {
JdbcTemplate jdbcTemplate = new JdbcTemplate();
    public List<List<String>> findAllSinceLastTargetActivity(int lastRow, List<String> values, String tableName) {
        String sql = "SELECT * FROM "+ tableName +" LIMIT "+ lastRow +",100";
        try {
            List<List<String>> lists = new ArrayList<>();
            List<EntitySbertest> sberTest2s = new ArrayList<>();
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, lastRow);

            for (Map<String, Object> row : rows) {
                List<String> valueList = new ArrayList<>();
                for(int i = 0; i < values.size(); i++) {
                    valueList.add(row.get(values.get(i)).toString());
                }
                lists.add(valueList);
            }
            return lists;
        } catch (Exception e){
            return Collections.EMPTY_LIST;
        }
    }

    public void saveAll(final List<List<String>> employeeList, List<String> types){
        try {
            TypesParser typesParser = new TypesParser();
            final int batchSize = 500;
            List<List<List<String>>> batchLists = Lists.partition(employeeList, batchSize);
            for (List<List<String>> batch : batchLists) {
                jdbcTemplate.batchUpdate(" ", new BatchPreparedStatementSetter() {
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
        } catch (Exception e){
            return;
        }
    }

    public String getLastValue(String tableName, String uniqueValue) {
        String sql = "SELECT * FROM "+ tableName +" ORDER BY "+ uniqueValue +" DESC LIMIT 1;";
        String lastValue = jdbcTemplate.queryForObject(sql, new Object[]{}, String.class);
        return lastValue;
    }

    public Integer getNumberLastTargetString(String tableName, String uniqueValue, String uniqueString, List<String> list){
        String as = null;
        for(int i = 0; i < list.size(); i ++) {
            as += (list.get(i) + ", ");
            as = StringUtils.chop(as);
            as = StringUtils.chop(as);
        }
        String sql = "SELECT row_number() OVER (ORDER BY "+ uniqueString +") "+ as +" \n" +
                "FROM "+ tableName +" \n" +
                "WHERE "+ uniqueString +"=?;";

        Integer rowLastValue = jdbcTemplate.queryForObject(sql, new Object[]{}, Integer.class);
        return rowLastValue;
    }
}
