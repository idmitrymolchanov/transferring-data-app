package psychotest.inner_datasource.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLiteConfig {
    private static SQLiteConfig instance;
    private Connection connection;

    private SQLiteConfig() {
        connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e) {
        }
    }

    public static Connection getConnection() {
        instance = new SQLiteConfig();
        return instance.connection;
    }
}
