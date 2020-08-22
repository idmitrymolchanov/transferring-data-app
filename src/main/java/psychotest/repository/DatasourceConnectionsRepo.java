package psychotest.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import psychotest.entity.DatasourceEntityConnection;
import psychotest.inner_datasource.config.SQLiteConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("local")
public class DatasourceConnectionsRepo {

    public void saveDatasourceTable(DatasourceEntityConnection datasource) {
        String sql = "INSERT INTO DATASOURCE_CONNECTIONS (source_uri,target_uri) VALUES (?,?);";
        try (Connection conn = SQLiteConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, datasource.getSource_url());
            pstmt.setString(2, datasource.getTarget_url());
            pstmt.executeUpdate();
        } catch (SQLException e) { }
    }

    public List<DatasourceEntityConnection> getAllConnections() {
        String sql = "SELECT * FROM DATASOURCE_CONNECTIONS;";
        List<DatasourceEntityConnection> entityList = new ArrayList<>();
        try (Connection conn = SQLiteConfig.getConnection();
             PreparedStatement stmt  = conn.prepareStatement(sql)) {
             ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DatasourceEntityConnection entity = new DatasourceEntityConnection();
                entity.setId(rs.getLong("id"));
                entity.setSource_url("source_uri");
                entity.setTarget_url("target_uri");
                entityList.add(entity);
            }
        } catch (SQLException e) { }
        return entityList;
    }

}
