package psychotest.repository;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import psychotest.entity.RegistrationEntity;

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

    public List<RegistrationEntity> findByIdUser(Long id){
        try {
            sqlSelect = "select * from "+ sourceTableName +" where id = ?;";
            List<RegistrationEntity> sberTest1s = new ArrayList<RegistrationEntity>();

            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlSelect, id);

            for (Map<String, Object> row : rows) {
                RegistrationEntity obj = new RegistrationEntity();
                        obj.setId((Long) row.get("id"));
                        obj.setUsername((String) row.get("username"));
                        obj.setPassword((String) row.get("password"));
                        obj.setPasswordCONFIRM((String) row.get("passwordCONFIRM"));
                sberTest1s.add(obj);
            }
            return sberTest1s;
        } catch (Exception e){
            return Collections.emptyList();
        }
    }

    public void saveAllUser(final List<RegistrationEntity> employeeList){
        try {
            sqlInsert = "INSERT INTO "+ sourceTableName +"(id, username, password, passwordCONFIRM) VALUES(?,?,?,?);";
            final int batchSize = 500;
            List<List<RegistrationEntity>> batchLists = Lists.partition(employeeList, batchSize);
            for (List<RegistrationEntity> batch : batchLists) {
                jdbcTemplate.batchUpdate(sqlInsert, new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i)
                            throws SQLException {
                        RegistrationEntity entitySbertest = batch.get(i);
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
