package psychotest.repository;

import org.springframework.stereotype.Repository;
import psychotest.entity.UserEntity;
import psychotest.inner_datasource.config.SQLiteConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsersDAOImpl implements UsersDAO {

    @Override
    public List<UserEntity> getAllUsers() {
        String sql = "SELECT (id, user_name) FROM APP_USER";
        List<UserEntity> list = new ArrayList<>();
        try (Connection conn = SQLiteConfig.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            while (rs.next()) {
                UserEntity entity = new UserEntity();
                entity.setId(rs.getLong("USER_ID"));
                entity.setUsername(rs.getString("USER_NAME"));
                list.add(entity);
            }
        } catch (SQLException e) { }
        return list;
    }
}
