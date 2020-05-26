package psychotest.repository;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import psychotest.entity.EntityUser;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@Profile("local")
public class RoleRepo {
    private static String sourceTableName;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoleRepo(@Qualifier("jdbcTemplateUser") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${datasource.three.name}")
    public void setSourceTableName(String sourceTableName) {
        this.sourceTableName = sourceTableName;
    }

    private String sqlSelect;
    private String sqlInsert;

    public List<EntityUser> findByIdUser(Long id){
        try {
            sqlSelect = "select * from "+ sourceTableName +" where id = ?;";
            List<EntityUser> sberTest1s = new ArrayList<EntityUser>();

            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlSelect, id);

            for (Map<String, Object> row : rows) {
                EntityUser entitySbertest = EntityUser
                        .builder()
                        .id((Long) row.get("id"))
                        .username((String) row.get("username"))
                        .password((String) row.get("password"))
                        .passwordCONFIRM((String) row.get("passwordCONFIRM"))
                        .build();
                sberTest1s.add(entitySbertest);
            }
            return sberTest1s;
        } catch (Exception e){
            return Collections.emptyList();
        }
    }

    public void saveAllUser(final List<EntityUser> employeeList){
        try {
            sqlInsert = "INSERT INTO "+ sourceTableName +"(id, username, password, passwordCONFIRM) VALUES(?,?,?,?);";
            final int batchSize = 500;
            List<List<EntityUser>> batchLists = Lists.partition(employeeList, batchSize);
            for (List<EntityUser> batch : batchLists) {
                jdbcTemplate.batchUpdate(sqlInsert, new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i)
                            throws SQLException {
                        EntityUser entitySbertest = batch.get(i);
                        ps.setLong(1, entitySbertest.getId());
                        ps.setString(2, entitySbertest.getUsername());
                        ps.setString(3, entitySbertest.getPassword());
                        ps.setString(4, entitySbertest.getPasswordCONFIRM());
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
}
