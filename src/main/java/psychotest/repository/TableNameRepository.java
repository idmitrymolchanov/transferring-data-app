package psychotest.repository;

import org.springframework.stereotype.Repository;
import psychotest.entity.TableNameEntity;
import psychotest.inner_datasource.config.SQLiteConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TableNameRepository {

    private Connection connect() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e) {
        }
        return c;
    }

    public void saveTableName(TableNameEntity tableNameEntity) {
        String sql = "INSERT INTO TABLE_NAME (name_table) VALUES (?);";
        try (Connection conn = SQLiteConfig.getConnection(); //this.connect()
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tableNameEntity.getTableName());
            pstmt.executeUpdate();
        } catch (SQLException e) { }
    }

    public String findLastByName() {
        String sql = "SELECT name_table FROM TABLE_NAME ORDER BY id DESC LIMIT 1;";
        String tableName = null;
        try (Connection conn = SQLiteConfig.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            while (rs.next()) {
                tableName = rs.getString("name_table");
            }
        } catch (SQLException e) { }
        return tableName;
    }

    public List<TableNameEntity> getAllTable() {
        String sql = "SELECT * FROM TABLE_NAME";
        List<TableNameEntity> list = new ArrayList<>();
        try (Connection conn = SQLiteConfig.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            while (rs.next()) {
                TableNameEntity entity = new TableNameEntity();
                entity.setId(rs.getLong("id"));
                entity.setTableName(rs.getString("name_table"));
                list.add(entity);
            }
        } catch (SQLException e) { }
        return list;
    }

}
