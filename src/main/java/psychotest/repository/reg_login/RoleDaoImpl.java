package psychotest.repository.reg_login;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import psychotest.inner_datasource.config.SQLiteConfig;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Override
    public List<String> getRoleNames(Long userId) {
        String sql = "Select r.Role_Name " //
                + " from User_Role ur, App_Role r " //
                + " where ur.Role_Id = r.Role_Id and ur.User_Id = ? ";
        List<String> roles = new ArrayList<>();
        try (Connection conn = SQLiteConfig.getConnection();
             PreparedStatement stmt  = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                roles.add(rs.getString("Role_Name"));
            }
        } catch (SQLException e) { }

        return roles;
    }
}