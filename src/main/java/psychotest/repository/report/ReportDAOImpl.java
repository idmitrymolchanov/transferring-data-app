package psychotest.repository.report;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import psychotest.entity.ReportEntity;
import psychotest.inner_datasource.config.SQLiteConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@Profile("local")
public class ReportDAOImpl implements ReportDAO {

    @Override
    public void saveTransaction(ReportEntity reportEntity) {
        String sql = "INSERT INTO REPORT_TABLE (source_url,target_url,table_name,date_time,result) VALUES (?,?,?,?,?);";
        try (Connection conn = SQLiteConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, reportEntity.getSource_url());
            pstmt.setString(2, reportEntity.getTarget_url());
            pstmt.setString(3, reportEntity.getTable_name());
            pstmt.setString(3, reportEntity.getDate_time());
            pstmt.setString(3, reportEntity.getResult());
            pstmt.executeUpdate();
        } catch (SQLException ignored) { }
    }

    @Override
    public ReportEntity getTransactions() {
        return null;
    }

    @Override
    public List<ReportEntity> getAll() {
        String sql = "SELECT * FROM REPORT_TABLE;";
        List<ReportEntity> reportEntities = new ArrayList<>();

        try (Connection connection = SQLiteConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                ReportEntity entity = new ReportEntity();
                entity.setId(rs.getInt("id"));
                entity.setSource_url(rs.getString("source_url"));
                entity.setTarget_url(rs.getString("target_url"));
                entity.setSource_url(rs.getString("table_name"));
                entity.setTarget_url(rs.getString("date_time"));
                entity.setResult(rs.getString("result"));
                reportEntities.add(entity);
            }

        } catch (Exception e){
            return Collections.EMPTY_LIST;
        }

        return reportEntities;
    }
}
