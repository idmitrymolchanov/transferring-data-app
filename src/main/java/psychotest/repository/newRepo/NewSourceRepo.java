package psychotest.repository.newRepo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class NewSourceRepo {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NewSourceRepo(@Qualifier("jdbcTemplateSource") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<List<String>> findAllSinceLastTargetActivity(int lastRow, List<String> values, String tableName) {
        String sql = "SELECT * FROM "+ tableName +" LIMIT "+ lastRow +", 100;";
        System.out.println("in method");
        try {
            List<List<String>> lists = new ArrayList<>();
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

            for (Map<String, Object> row : rows) {
                List<String> valueList = new ArrayList<>();
                for(int i = 0; i < values.size(); i++) {
                    valueList.add(row.get(values.get(i)).toString());
                }
                lists.add(valueList);
            }
            System.out.println("+++++++++");
            System.out.println(lists.get(0).get(2));
            return lists;
        } catch (Exception e){
            return Collections.EMPTY_LIST;
        }
    }

    public Integer getNumberLastString(String tableName, String uniqueValue, String uniqueString, List<String> list){
        String as = "";
        for(int i = 0; i < list.size(); i ++) {
            as += (list.get(i) + ", ");
        }
        as = StringUtils.chop(as);
        as = StringUtils.chop(as);
        System.out.println(as);
       /* String sql = "SELECT row_number() OVER (ORDER BY "+ uniqueString +") as "+ as +" \n" +
                " FROM "+ tableName +" \n" +
                " WHERE "+ uniqueString +"="+uniqueValue+";";*/
        jdbcTemplate.execute("SET @i = 0;");
        String sql = "SELECT r FROM (" +
                "SELECT @i := @i + 1 AS r, id as d " +
                "FROM "+tableName+" as z ORDER BY id ASC) as m WHERE d=15;";

        Integer rowLastValue = jdbcTemplate.queryForObject(sql, new Object[]{}, Integer.class);
        return rowLastValue;
    }
}
