package psychotest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;
import psychotest.entity.DatasourceEntity;

import java.sql.*;
import java.util.List;


@Repository
@Profile("local")
public class MainRepository {

    private Connection connect() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e) {
        }
        return c;
    }

    public void saveDatasourceTable(DatasourceEntity datasourceEntity) {
        System.out.println(datasourceEntity.getRole());
        String sql = "INSERT INTO DATASOURCE_SETTING (driver_name,url,username,password,role) VALUES (?,?,?,?,?);";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            System.out.println(datasourceEntity.getRole() + " " + datasourceEntity.getDriver_name());
            pstmt.setString(1, datasourceEntity.getDriver_name());
            pstmt.setString(2, datasourceEntity.getUrl());
            pstmt.setString(3, datasourceEntity.getUsername());
            pstmt.setString(4, datasourceEntity.getPassword());
            pstmt.setString(5, datasourceEntity.getRole());
            pstmt.executeUpdate();
        } catch (SQLException e) { }
    }

    public DatasourceEntity getDatasourceById(Integer id) {
        String sql = "SELECT * FROM DATASOURCE_SETTING WHERE id=?;";
        DatasourceEntity entity = new DatasourceEntity();
        try (Connection conn = this.connect();
             PreparedStatement stmt  = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                entity.setId(rs.getLong("id"));
                entity.setDriver_name(rs.getString("driver_name"));
                entity.setUrl(rs.getString("url"));
                entity.setUsername(rs.getString("username"));
                entity.setPassword(rs.getString("password"));
                entity.setRole(rs.getString("role"));
            }
        } catch (SQLException e) { }
        return entity;
    }

    public String findLastDataSourceByUrl() {
        String sql = "SELECT url FROM DATASOURCE_SETTING ORDER BY id DESC LIMIT 1;";
        String tableName = null;
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            while (rs.next()) {
                tableName = rs.getString("url");
            }
        } catch (SQLException e) { }
        return tableName;
    }
}
