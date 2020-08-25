package psychotest.repository;

import org.springframework.stereotype.Repository;
import psychotest.entity.ValueTypeEntity;
import psychotest.inner_datasource.config.SQLiteConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TypeValueDAOImpl implements TypeValueDAO {

    @Override
    public List<ValueTypeEntity> findByTableName(String tableName) {
        String sql = "SELECT * FROM TYPE_AND_VALUE WHERE string_table_name=?;";
        List<ValueTypeEntity> list = new ArrayList<>();
        try (Connection conn = SQLiteConfig.getConnection();
             PreparedStatement stmt  = conn.prepareStatement(sql)) {
            stmt.setString(1, tableName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ValueTypeEntity valueTypeEntity = new ValueTypeEntity();
                valueTypeEntity.setId(rs.getLong("id"));
                valueTypeEntity.setStringTableName(rs.getString("string_table_name"));
                valueTypeEntity.setStringValue(rs.getString("string_value"));
                valueTypeEntity.setStringType(rs.getString("string_type"));
                list.add(valueTypeEntity);
            }

        } catch (SQLException e) { }
        return list;
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM TYPE_AND_VALUE WHERE id = ?;";
        try (Connection conn = SQLiteConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) { }
    }

    @Override
    public ValueTypeEntity findById(Integer id) {
        String sql = "SELECT * FROM TYPE_AND_VALUE WHERE id=?;";
        ValueTypeEntity valueTypeEntity = new ValueTypeEntity();
        try (Connection conn = SQLiteConfig.getConnection();
             PreparedStatement stmt  = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                valueTypeEntity.setId(rs.getLong("id"));
                valueTypeEntity.setStringTableName(rs.getString("string_table_name"));
                valueTypeEntity.setStringValue(rs.getString("string_value"));
                valueTypeEntity.setStringType(rs.getString("string_type"));
            }

        } catch (SQLException e) { }
        return valueTypeEntity;
    }

    @Override
    public void saveTypeAndValue(ValueTypeEntity valueTypeEntity) {
        String sql = "INSERT INTO TYPE_AND_VALUE (string_table_name,string_value,string_type) VALUES (?,?,?);";
        try (Connection conn = SQLiteConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, valueTypeEntity.getStringTableName());
            pstmt.setString(2, valueTypeEntity.getStringValue());
            pstmt.setString(3, valueTypeEntity.getStringType());
            pstmt.executeUpdate();
        } catch (SQLException e) { }
    }

    @Override
    public void deleteTableById(Integer id) {
        String sql = "DELETE FROM TYPE_AND_VALUE WHERE id = ?;";
        try (Connection conn = SQLiteConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) { }
    }

}
