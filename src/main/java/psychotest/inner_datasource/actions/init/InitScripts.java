package psychotest.inner_datasource.actions.init;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@Slf4j
public class InitScripts {
    Connection connection;
    Statement statement;

    public InitScripts() {
        connection = null;
        statement = null;
    }

    private void createConnection(String value) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            log.info("Opened database successfully");
            statement = connection.createStatement();
            statement.executeUpdate(value);

            statement.close();
            connection.close();
        } catch ( Exception e ) {
            log.error(e.getClass().getName() + ": " + e.getMessage());
        }

        log.info("Datasource table created successfully");
    }

    public void setConnection(String value) {
        createConnection(value);
    }
}
