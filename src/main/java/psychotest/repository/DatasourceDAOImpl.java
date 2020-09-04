package psychotest.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import psychotest.entity.DatasourceEntity;
import psychotest.inner_datasource.config.SQLiteConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("local")
public class DatasourceDAOImpl implements DatasourceDAO {

    @Override
    public void saveDatasourceTable(DatasourceEntity datasourceEntity) {
        String sql = "INSERT INTO DATASOURCE_SETTING (driver_name,url,username,password) VALUES (?,?,?,?);";
        try (Connection conn = SQLiteConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, datasourceEntity.getDriver_name());
            pstmt.setString(2, datasourceEntity.getUrl());
            pstmt.setString(3, datasourceEntity.getUsername());
            pstmt.setString(4, datasourceEntity.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) { }
    }

    @Override
    public DatasourceEntity getDatasourceById(Integer id) {
        String sql = "SELECT * FROM DATASOURCE_SETTING WHERE id=?;";
        DatasourceEntity entity = new DatasourceEntity();
        try (Connection conn = SQLiteConfig.getConnection();
             PreparedStatement stmt  = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                entity.setId(rs.getLong("id"));
                entity.setDriver_name(rs.getString("driver_name"));
                entity.setUrl(rs.getString("url"));
                entity.setUsername(rs.getString("username"));
                entity.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) { }
        return entity;
    }

    @Override
    public DatasourceEntity getDatasourceByURL(String url) {
        String sql = "SELECT * FROM DATASOURCE_SETTING WHERE url=?;";
        DatasourceEntity entity = new DatasourceEntity();
        try (Connection conn = SQLiteConfig.getConnection();
             PreparedStatement stmt  = conn.prepareStatement(sql)) {
            stmt.setString(1, url);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                entity.setId(rs.getLong("id"));
                entity.setDriver_name(rs.getString("driver_name"));
                entity.setUrl(rs.getString("url"));
                entity.setUsername(rs.getString("username"));
                entity.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) { }
        return entity;
    }

    @Override
    public String findLastDataSourceByUrl() {
        String sql = "SELECT url FROM DATASOURCE_SETTING ORDER BY id DESC LIMIT 1;";
        String tableName = null;
        try (Connection conn = SQLiteConfig.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            while (rs.next()) {
                tableName = rs.getString("url");
            }
        } catch (SQLException e) { }
        return tableName;
    }

    @Override
    public List<String> getDatasourceBySourceRole(String sourceRole) {
        String sql = "SELECT * FROM DATASOURCE_SETTING WHERE role=?;";
        List<String> urlSourceNameList = new ArrayList<>();
        try (Connection conn = SQLiteConfig.getConnection();
             PreparedStatement stmt  = conn.prepareStatement(sql)) {
            stmt.setString(1, sourceRole);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                urlSourceNameList.add(rs.getString("url"));
            }
        } catch (SQLException e) { }
        return urlSourceNameList;
    }
}
