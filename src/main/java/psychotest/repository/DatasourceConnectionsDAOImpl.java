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
public class DatasourceConnectionsDAOImpl implements DatasourceConnectionsDAO {

    @Override
    public void saveDatasourceTable(DatasourceEntityConnection datasource) {
        String sql = "INSERT INTO DATASOURCE_CONNECTIONS (source_url,target_url,hash_connection) VALUES (?,?,?);";
        try (Connection conn = SQLiteConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, datasource.getSource_url());
            pstmt.setString(2, datasource.getTarget_url());
            pstmt.setString(3, datasource.getHash_connection());
            pstmt.executeUpdate();
        } catch (SQLException e) { }
    }

    @Override
    public List<DatasourceEntityConnection> getAllConnections() {
        String sql = "SELECT id,source_url,target_url FROM DATASOURCE_CONNECTIONS;";
        List<DatasourceEntityConnection> entityList = new ArrayList<>();
        try (Connection conn = SQLiteConfig.getConnection();
             PreparedStatement stmt  = conn.prepareStatement(sql)) {
             ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DatasourceEntityConnection entity = new DatasourceEntityConnection();
                entity.setId(rs.getLong("id"));
                entity.setSource_url(rs.getString("source_url"));
                entity.setTarget_url(rs.getString("target_url"));
                entityList.add(entity);
            }
        } catch (SQLException e) { }
        return entityList;
    }

    @Override
    public String getHashConnection(Long id) {
        String sql = "SELECT hash_connection FROM DATASOURCE_CONNECTIONS WHERE id=?;";
        String hashConnection = null;
        try (Connection conn = SQLiteConfig.getConnection();
             PreparedStatement stmt  = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                hashConnection = rs.getString("hash_connection");
            }
        } catch (SQLException e) { }
        return hashConnection;
    }
}
