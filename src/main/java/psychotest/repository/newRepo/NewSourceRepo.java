package psychotest.repository.newRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import psychotest.entity.EntitySbertest;

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
}
