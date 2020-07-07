package psychotest.repository;

import org.springframework.stereotype.Repository;
import psychotest.entity.UserEntity;
import psychotest.service.UserMapper;

import java.sql.*;

@Repository
public class UserDAO {
/*
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(@Qualifier("jdbcTemplateSource") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }*/

    private Connection connect() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e) {
        }
        return c;
    }

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
    /*
    public UserEntity findUserAccount(String userName) {
        // Select .. from App_User u Where u.User_Name = ?
        String sql = UserMapper.BASE_SQL + " where u.User_Name = ? ";

        Object[] params = new Object[] { userName };
        UserMapper mapper = new UserMapper();
        try {
            UserEntity userInfo = jdbcTemplate.queryForObject(sql, params, mapper);
            return userInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }*/

}