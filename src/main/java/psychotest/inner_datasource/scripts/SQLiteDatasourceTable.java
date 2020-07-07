package psychotest.inner_datasource.scripts;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@Slf4j
public class SQLiteDatasourceTable {
    private static final String DATASOURCE_SETTING = "CREATE TABLE DATASOURCE_SETTING " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " driver_name VARCHAR(255), " +
            " url VARCHAR(255) UNIQUE , " +
            " username VARCHAR(255), " +
            " password VARCHAR(255)," +
            " role VARCHAR(255));";

    public void createTable( String args[] ) {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            log.info("Opened database successfully");
            stmt = c.createStatement();
            stmt.executeUpdate(DATASOURCE_SETTING);

            stmt.close();
            c.close();
        } catch ( Exception e ) {
            log.error(e.getClass().getName() + ": " + e.getMessage());
        }

        log.info("Datasource table created successfully");
    }
}