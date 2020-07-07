package psychotest.inner_datasource.scripts;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@Slf4j
public class SecurityScript {
    private static String script = "" +
            "create table APP_USER\n" +
            "(USER_ID           INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            " USER_NAME         VARCHAR(36) UNIQUE NOT NULL ,\n" +
            " ENCRYTED_PASSWORD VARCHAR(128) NOT NULL ,\n" +
            " ENABLED           INTEGER NOT NULL) ;\n" +
            "\n" +
            "create table APP_ROLE\n" +
            "(ROLE_ID   INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            " ROLE_NAME VARCHAR(30) UNIQUE not null) ;\n" +
            "\n" +
            "create table USER_ROLE\n" +
            "(ID      INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            " USER_ID INTEGER not null,\n" +
            " ROLE_ID INTEGER not null," +
            " unique (USER_ID, ROLE_ID)," +
            " foreign key (USER_ID) references APP_USER (USER_ID)," +
            " foreign key (ROLE_ID) references APP_ROLE (ROLE_ID));\n" +
            "\n" +
            "CREATE TABLE Persistent_Logins (\n" +
            " username varchar(64) not null,\n" +
            " series varchar(64) not null,\n" +
            " token varchar(64) not null,\n" +
            " last_used timestamp not null,\n" +
            " PRIMARY KEY (series));\n" +
            "\n" +
            "insert into App_User (USER_ID, USER_NAME, ENCRYTED_PASSWORD, ENABLED) values (2, 'dbuser1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);\n" +
            "\n" +
            "insert into App_User (USER_ID, USER_NAME, ENCRYTED_PASSWORD, ENABLED) values (1, 'dbadmin1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);\n" +
            "\n" +
            "insert into app_role (ROLE_ID, ROLE_NAME) values (1, 'ROLE_ADMIN');\n" +
            "\n" +
            "insert into app_role (ROLE_ID, ROLE_NAME) values (2, 'ROLE_USER');\n" +
            "\n" +
            "insert into user_role (ID, USER_ID, ROLE_ID) values (1, 1, 1);\n" +
            "\n" +
            "insert into user_role (ID, USER_ID, ROLE_ID) values (2, 1, 2);\n" +
            "\n" +
            "insert into user_role (ID, USER_ID, ROLE_ID) values (3, 2, 2);";

    public static void main( String args[] ) {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            log.info("Opened database successfully");

            stmt = c.createStatement();

            stmt.executeUpdate(script);

            stmt.close();
            c.close();
        } catch ( Exception e ) {
            log.error(e.getClass().getName() + ": " + e.getMessage());
        }

        log.info("Table created successfully");
    }
}
