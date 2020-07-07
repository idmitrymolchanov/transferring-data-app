package psychotest.inner_datasource.scripts;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class SQLiteTableCreated {
    private static final String TABLE_NAME = "CREATE TABLE TABLE_NAME " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " name_table VARCHAR(255) UNIQUE);";
    private static final String TYPE_AND_VALUE = "CREATE TABLE TYPE_AND_VALUE " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " string_table_name VARCHAR(255), " +
            " string_value VARCHAR(255), " +
            " string_type VARCHAR(255), " +
            " FOREIGN KEY(string_table_name) REFERENCES TABLE_NAME(name_table)," +
            " UNIQUE (string_table_name, string_value));";
    private static final String UNIQ_AND_NOT_NULL = "CREATE TABLE UNIQ_VALUES " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " string_table_name VARCHAR(255), " +
            " string_value VARCHAR(255), " +
            " unique_value INTEGER, " +
            "FOREIGN KEY(string_table_name, string_value) REFERENCES TYPE_AND_VALUE(string_table_name, string_value));";
    private static final String SCHEDULER = "CREATE TABLE SCHEDULER " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " string_table_name VARCHAR(255), " +
            " date_value VARCHAR(255), " +
            " period_value VARCHAR(255), " +
            " FOREIGN KEY(string_table_name) REFERENCES TABLE_NAME(name_table));";

    public void createTable( String args[] ) {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            log.info("Opened database successfully");

            stmt = c.createStatement();

            stmt.executeUpdate(TABLE_NAME);
            stmt.executeUpdate(TYPE_AND_VALUE);
            stmt.executeUpdate(UNIQ_AND_NOT_NULL);
            stmt.executeUpdate(SCHEDULER);

            stmt.close();
            c.close();
        } catch ( Exception e ) {
            log.error(e.getClass().getName() + ": " + e.getMessage());
        }

        log.info("Table created successfully");
    }
}
