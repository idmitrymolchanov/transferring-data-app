package psychotest.inner_datasource.actions.domain;

public class SecurityScriptsDomain {

    private final String APP_USER_SCRIPT =
            "create table APP_USER\n" +
                    "(USER_ID           INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    " USER_NAME         VARCHAR(36) UNIQUE NOT NULL ,\n" +
                    " ENCRYTED_PASSWORD VARCHAR(128) NOT NULL ,\n" +
                    " ENABLED           INTEGER NOT NULL) ;\n";

    private final String APP_ROLE_SCRIPT =
            "create table APP_ROLE\n" +
                    "(ROLE_ID   INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    " ROLE_NAME VARCHAR(30) UNIQUE not null) ;\n";

    private final String USER_ROLE_SCRIPT =
            "create table USER_ROLE\n" +
                    "(ID      INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    " USER_ID INTEGER not null,\n" +
                    " ROLE_ID INTEGER not null," +
                    " unique (USER_ID, ROLE_ID)," +
                    " foreign key (USER_ID) references APP_USER (USER_ID)," +
                    " foreign key (ROLE_ID) references APP_ROLE (ROLE_ID));";

    private final String PERSISTENT_LOGINS_SCRIPT =
            "CREATE TABLE Persistent_Logins (\n" +
                    " username varchar(64) not null,\n" +
                    " series varchar(64) not null,\n" +
                    " token varchar(64) not null,\n" +
                    " last_used timestamp not null,\n" +
                    " PRIMARY KEY (series));\n";

    public String getAPP_USER_SCRIPT() {
        return APP_USER_SCRIPT;
    }

    public String getAPP_ROLE_SCRIPT() {
        return APP_ROLE_SCRIPT;
    }

    public String getUSER_ROLE_SCRIPT() {
        return USER_ROLE_SCRIPT;
    }

    public String getPERSISTENT_LOGINS_SCRIPT() {
        return PERSISTENT_LOGINS_SCRIPT;
    }
}
