package psychotest.repository;

import org.springframework.stereotype.Repository;
import psychotest.entity.ValueTypeEntity;
import psychotest.inner_datasource.config.SQLiteConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MainRequestDAOImpl implements MainRequestDAO {

    @Override
    public List<ValueTypeEntity> getTypeAndValue(String hashTableName) {
        String sql = "SELECT * FROM TYPE_AND_VALUE WHERE hash_table_name=? ORDER BY id;";
        List<ValueTypeEntity> list = new ArrayList<>();
        try (Connection conn = SQLiteConfig.getConnection();
             PreparedStatement stmt  = conn.prepareStatement(sql)) {
            stmt.setString(1, hashTableName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ValueTypeEntity valueTypeEntity = new ValueTypeEntity();
                valueTypeEntity.setId(rs.getLong("id"));
                valueTypeEntity.setHashTableName(rs.getString("hash_table_name"));
                valueTypeEntity.setStringValue(rs.getString("string_value"));
                valueTypeEntity.setStringType(rs.getString("string_type"));
                list.add(valueTypeEntity);
            }

        } catch (SQLException e) { }
        return list;
    }

    @Override
    public String getUniqueValue(String hashTableName) {
        String sql = "SELECT string_value FROM UNIQUE_VALUES WHERE hash_table_name=?;";
        String uniqueValue;
        try (Connection conn = SQLiteConfig.getConnection();
             PreparedStatement stmt  = conn.prepareStatement(sql)) {
            stmt.setString(1, hashTableName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                return rs.getString("string_value");

        } catch (SQLException e) { }
        return "";
    }
}
