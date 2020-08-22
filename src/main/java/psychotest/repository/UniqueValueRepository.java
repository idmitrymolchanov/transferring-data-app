package psychotest.repository;

import org.springframework.stereotype.Repository;
import psychotest.entity.UniqueValuesEntity;
import psychotest.inner_datasource.config.SQLiteConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class UniqueValueRepository {

    public void saveUniqueValue(UniqueValuesEntity value) {
        String sql = "INSERT INTO UNIQ_VALUES (string_table_name,string_value,unique_value) VALUES (?,?,?);";
        try (Connection conn = SQLiteConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int val = (value.isUnique_value()) ? 1 : 0;
            System.out.println(value.getStringTableName() + " " + value.getStringValue() + " " + value.isUnique_value());
            pstmt.setString(1, value.getStringTableName());
            pstmt.setString(2, value.getStringValue());
            pstmt.setInt(3, val);
            pstmt.executeUpdate();
        } catch (SQLException e) { }
    }
}
