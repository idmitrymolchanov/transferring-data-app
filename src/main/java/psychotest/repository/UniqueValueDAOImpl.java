package psychotest.repository;

import org.springframework.stereotype.Repository;
import psychotest.entity.UniqueValuesEntity;
import psychotest.inner_datasource.config.SQLiteConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class UniqueValueDAOImpl implements UniqueValueDAO {

    @Override
    public void saveUniqueValue(UniqueValuesEntity value) {
        String sql = "INSERT INTO UNIQUE_VALUES (hash_table_name,string_value) VALUES (?,?);";
        try (Connection conn = SQLiteConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, value.getHashTableName());
            pstmt.setString(2, value.getStringValue());
            pstmt.executeUpdate();
        } catch (SQLException e) { }
    }
}
