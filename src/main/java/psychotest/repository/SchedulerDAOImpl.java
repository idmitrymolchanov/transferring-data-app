package psychotest.repository;

import org.springframework.stereotype.Repository;
import psychotest.entity.SchedulerEntity;
import psychotest.entity.ValueTypeEntity;
import psychotest.inner_datasource.config.SQLiteConfig;
import psychotest.parser.Parser;
import psychotest.parser.SchedulerParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class SchedulerDAOImpl implements SchedulerDAO {

    @Override
    public void saveTableName(SchedulerEntity entity) {
        String sql = "INSERT INTO SCHEDULER (hash_table_name,date_value,period_value,remainder) VALUES (?,?,?,?);";
        try (Connection conn = SQLiteConfig.getConnection(); //this.connect()
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, entity.getHashTableName());
            pstmt.setString(2, entity.getDateValue());
            pstmt.setString(3, entity.getPeriodValue());
            pstmt.setLong(4, entity.getRemainder());
            pstmt.executeUpdate();
        } catch (SQLException e) { }
    }

    @Override
    public SchedulerEntity findSchedulerObjectByTableHash(String table_hash) {
        String sql = "SELECT * FROM SCHEDULER WHERE hash_table_name=?;";
        SchedulerEntity entity = new SchedulerEntity();
        try (Connection conn = SQLiteConfig.getConnection();
             PreparedStatement stmt  = conn.prepareStatement(sql)) {
            stmt.setString(1, table_hash);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                entity.setId(rs.getLong("id"));
                entity.setHashTableName(rs.getString("hash_table_name"));
                entity.setDateValue(rs.getString("date_value"));
                entity.setPeriodValue(rs.getString("period_value"));
                entity.setRemainder(rs.getLong("remainder"));
            }
        } catch (SQLException ignored) { }
        return entity;
    }

    @Override
    public void updateRemainder(String hashTableName) {
        String sql = "UPDATE SCHEDULER SET remainder = ? WHERE hash_table_name=?;";
        try (Connection conn = SQLiteConfig.getConnection();
             PreparedStatement stmt  = conn.prepareStatement(sql)) {
            stmt.setLong(1, getRemainder(hashTableName));
            stmt.setString(1, hashTableName);

            ResultSet rs = stmt.executeQuery();
        } catch (SQLException e) { }
    }

    private Long getRemainder(String hashTableName) {
        String sql = "SELECT period_value,remainder FROM SCHEDULER WHERE hash_table_name=?;";

        int period = 0;
        int remainder = 0;

        try (Connection conn = SQLiteConfig.getConnection();
             PreparedStatement stmt  = conn.prepareStatement(sql)) {
            stmt.setString(1, hashTableName);
            ResultSet rs = stmt.executeQuery();
            Parser parser = new SchedulerParser();

            while (rs.next()) {
                period = parser.getCodeByValue(rs.getString("period_value"));
                remainder = rs.getInt("remainder");
            }

        } catch (SQLException e) { }
        return equalsPeriod(period, remainder);
    }

    private Long equalsPeriod(int period, int remainder) {
        remainder--;
        return (long) (remainder == 0 ? period : remainder);
    }
}
