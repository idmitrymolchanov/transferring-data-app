package psychotest.repository.reg_login;

import org.springframework.stereotype.Repository;
import psychotest.entity.UserEntity;
import psychotest.service.UserMapper;

import java.sql.*;

@Repository
public class UserDaoImpl implements UserDao {

    private Connection connect() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e) {
        }
        return c;
    }

    @Override
    public UserEntity findUserAccount(String userName) {
        String sql = UserMapper.BASE_SQL + " where u.User_Name = ? ";
        UserEntity userEntity = new UserEntity();
        try (Connection conn = this.connect();
             PreparedStatement stmt  = conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                userEntity.setId(rs.getLong("User_Id"));
                userEntity.setUsername(rs.getString("User_Name"));
                userEntity.setPassword(rs.getString("Encryted_Password"));
            }
        } catch (SQLException e) { }

        return userEntity;
    }

}