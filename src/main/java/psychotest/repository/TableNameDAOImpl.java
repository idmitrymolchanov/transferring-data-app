package psychotest.repository;

import org.springframework.stereotype.Repository;
import psychotest.entity.TableNameEntity;
import psychotest.inner_datasource.config.SQLiteConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TableNameDAOImpl implements TableNameDAO {

    @Override
    public void saveTableName(TableNameEntity tableNameEntity) {
        String sql = "INSERT INTO TABLE_NAME (name_table,hash_connection,hash_table_name) VALUES (?,?,?);";
        try (Connection conn = SQLiteConfig.getConnection(); //this.connect()
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tableNameEntity.getTableName());
            pstmt.setString(2, tableNameEntity.getHash_connection());
            pstmt.setString(3, tableNameEntity.getHash_table_name());
            pstmt.executeUpdate();
        } catch (SQLException e) { }
    }

    @Override
    public String findLastByName() {
        String sql = "SELECT hash_table_name FROM TABLE_NAME ORDER BY id DESC LIMIT 1;";
        String tableName = null;
        try (Connection conn = SQLiteConfig.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            while (rs.next()) {
                tableName = rs.getString("hash_table_name");
            }
        } catch (SQLException e) { }
        return tableName;
    }

    @Override
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
                entity.setHash_connection("hash_connection");
                entity.setHash_table_name("hash_table_name");
                list.add(entity);
            }
        } catch (SQLException e) { }
        return list;
    }

}
