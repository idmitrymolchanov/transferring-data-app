package psychotest.repository;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import psychotest.entity.HelpTableNameEntity;
import psychotest.entity.RegistrationEntity;
import psychotest.entity.UserEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@Profile("local")
public class RoleRepo {
   // private static String sourceTableName;
  //  private final JdbcTemplate jdbcTemplate;

 //   @Autowired
 //   public RoleRepo(@Qualifier("jdbcTemplateUser") JdbcTemplate jdbcTemplate) {
 //       this.jdbcTemplate = jdbcTemplate;
 //   }

    private Connection connect() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e) {
        }
        return c;
    }
/*
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
*/
    public void saveAll(UserEntity userEntity) {
        String sql = "INSERT INTO APP_USER (USER_NAME, ENCRYTED_PASSWORD, ENABLED) VALUES (?,?,?);";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            System.out.println("login = " + userEntity.getUsername() + " pass = " + userEntity.getPassword());
            pstmt.setString(1, userEntity.getUsername());
            pstmt.setString(2, userEntity.getPassword());
            pstmt.setInt(3, 1);
            pstmt.executeUpdate();
            saveRole(userEntity.getUsername());
        } catch (SQLException e) { }
    }

    public void saveRole(String role) {
        String sql = "INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (SELECT APP_USER.USER_ID FROM APP_USER WHERE APP_USER.USER_NAME=?,2);";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, role);
            pstmt.executeUpdate();
        } catch (SQLException e) { }
    }
}
