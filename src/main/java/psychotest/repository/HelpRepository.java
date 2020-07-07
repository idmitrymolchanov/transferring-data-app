package psychotest.repository;

import org.springframework.stereotype.Repository;
import psychotest.entity.UniqueValuesEntity;
import psychotest.entity.HelpTableNameEntity;
import psychotest.entity.HelpValueAndTypeEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HelpRepository {

    private Connection connect() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e) {
        }
        return c;
    }

    public void saveTableName(HelpTableNameEntity helpTableNameEntity) {
        String sql = "INSERT INTO TABLE_NAME (name_table) VALUES (?);";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, helpTableNameEntity.getTableName());
            pstmt.executeUpdate();
        } catch (SQLException e) { }
    }

    public String findLastByName() {
        String sql = "SELECT name_table FROM TABLE_NAME ORDER BY id DESC LIMIT 1;";
        String tableName = null;
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            while (rs.next()) {
                tableName = rs.getString("name_table");
            }
        } catch (SQLException e) { }
        return tableName;
    }

    public List<HelpValueAndTypeEntity> findByTableName(String tableName) {
        String sql = "SELECT * FROM TYPE_AND_VALUE WHERE string_table_name=?;";
        List<HelpValueAndTypeEntity> list = new ArrayList<>();
        try (Connection conn = this.connect();
             PreparedStatement stmt  = conn.prepareStatement(sql)) {
            stmt.setString(1, tableName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                HelpValueAndTypeEntity helpValueAndTypeEntity = new HelpValueAndTypeEntity();
                helpValueAndTypeEntity.setId(rs.getLong("id"));
                helpValueAndTypeEntity.setStringTableName(rs.getString("string_table_name"));
                helpValueAndTypeEntity.setStringValue(rs.getString("string_value"));
                helpValueAndTypeEntity.setStringType(rs.getString("string_type"));
                list.add(helpValueAndTypeEntity);
            }

        } catch (SQLException e) { }
        return list;
    }

    public void deleteById(Integer id) {
        String sql = "DELETE FROM TYPE_AND_VALUE WHERE id = ?;";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) { }
    }

    public HelpValueAndTypeEntity findById(Integer id) {
        String sql = "SELECT * FROM TYPE_AND_VALUE WHERE id=?;";
        HelpValueAndTypeEntity helpValueAndTypeEntity = new HelpValueAndTypeEntity();
        try (Connection conn = this.connect();
             PreparedStatement stmt  = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                helpValueAndTypeEntity.setId(rs.getLong("id"));
                helpValueAndTypeEntity.setStringTableName(rs.getString("string_table_name"));
                helpValueAndTypeEntity.setStringValue(rs.getString("string_value"));
                helpValueAndTypeEntity.setStringType(rs.getString("string_type"));
            }

        } catch (SQLException e) { }
        return helpValueAndTypeEntity;
    }

    public void saveTypeAndValue(HelpValueAndTypeEntity helpValueAndTypeEntity) {
        String sql = "INSERT INTO TYPE_AND_VALUE (string_table_name,string_value,string_type) VALUES (?,?,?);";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, helpValueAndTypeEntity.getStringTableName());
            pstmt.setString(2, helpValueAndTypeEntity.getStringValue());
            pstmt.setString(3, helpValueAndTypeEntity.getStringType());
            pstmt.executeUpdate();
        } catch (SQLException e) { }
    }

    public void saveUniqueValue(UniqueValuesEntity value) {
        String sql = "INSERT INTO UNIQ_VALUES (string_table_name,string_value,unique_value) VALUES (?,?,?);";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int val = (value.isUnique_value()) ? 1 : 0;
            System.out.println(value.getStringTableName() + " " + value.getStringValue() + " " + value.isUnique_value());
            pstmt.setString(1, value.getStringTableName());
            pstmt.setString(2, value.getStringValue());
            pstmt.setInt(3, val);
            pstmt.executeUpdate();
        } catch (SQLException e) { }
    }

    public void deleteTableById(Integer id) {
        String sql = "DELETE FROM TYPE_AND_VALUE WHERE id = ?;";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) { }
    }

    public List<HelpTableNameEntity> getAllTable() {
        String sql = "SELECT * FROM TABLE_NAME";
        List<HelpTableNameEntity> list = new ArrayList<>();
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            while (rs.next()) {
                HelpTableNameEntity entity = new HelpTableNameEntity();
                entity.setId(rs.getLong("id"));
                entity.setTableName(rs.getString("name_table"));
                list.add(entity);
            }
        } catch (SQLException e) { }
        return list;
    }
}
