package psychotest.repository.reg_login;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import psychotest.entity.UserEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
@Profile("local")
public class RegistrationRepository {

    private Connection connect() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e) {
        }
        return c;
    }

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
