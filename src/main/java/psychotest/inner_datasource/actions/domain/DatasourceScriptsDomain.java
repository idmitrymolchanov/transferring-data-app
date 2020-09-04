package psychotest.inner_datasource.actions.domain;

public class DatasourceScriptsDomain {

    private final String DATASOURCE_SETTING_SCRIPT = "CREATE TABLE DATASOURCE_SETTING " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " driver_name VARCHAR(255), " +
            " url VARCHAR(255) UNIQUE, " +
            " username VARCHAR(255), " +
            " password VARCHAR(255));";

    private final String DATASOURCE_CONNECTIONS_SCRIPT = "CREATE TABLE DATASOURCE_CONNECTIONS " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " source_url VARCHAR(255) NOT NULL, " +
            " target_url VARCHAR(255) NOT NULL, " +
            " hash_connection VARCHAR(255) UNIQUE NOT NULL, " +
            " UNIQUE (source_url, target_url), " +
            " FOREIGN KEY(source_url) REFERENCES DATASOURCE_SETTING(url), " +
            " FOREIGN KEY(target_url) REFERENCES DATASOURCE_SETTING(url));";

    public String getDATASOURCE_CONNECTIONS_SCRIPT() {
        return DATASOURCE_CONNECTIONS_SCRIPT;
    }

    public String getDATASOURCE_SETTING_SCRIPT() {
        return DATASOURCE_SETTING_SCRIPT;
    }
}
