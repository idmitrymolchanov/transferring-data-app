package psychotest.repository;

import org.springframework.stereotype.Repository;
import psychotest.entity.HelpValueAndTypeEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BuildRepository {
    private Connection connect() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e) {
        }
        return c;
    }

    public List<HelpValueAndTypeEntity> getTypeAndValue(String tableName) {
        String sql = "SELECT * FROM TYPE_AND_VALUE WHERE string_table_name=? ORDER BY id;";
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

    public String getUniqueValue(String tableName) {
        String sql = "SELECT string_value FROM UNIQ_VALUES WHERE string_table_name=?;";
        String uniqueValue = null;
        try (Connection conn = this.connect();
             PreparedStatement stmt  = conn.prepareStatement(sql)) {
            stmt.setString(1, tableName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                uniqueValue = rs.getString("string_value");
            }

        } catch (SQLException e) { }
        return uniqueValue;
    }
}
