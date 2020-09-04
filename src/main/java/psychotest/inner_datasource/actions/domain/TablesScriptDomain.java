package psychotest.inner_datasource.actions.domain;

public class TablesScriptDomain {
    private final String TABLE_NAME_SCRIPT = "CREATE TABLE TABLE_NAME " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " name_table VARCHAR(255) NOT NULL, " +
            " hash_connection VARCHAR(255) NOT NULL, " +
            " hash_table_name VARCHAR(255) UNIQUE NOT NULL," +
            " UNIQUE (name_table, hash_table_name)," +
            " FOREIGN KEY(hash_connection) REFERENCES DATASOURCE_CONNECTIONS(hash_connection));";

    private final String TYPE_AND_VALUE_SCRIPT = "CREATE TABLE TYPE_AND_VALUE " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " hash_table_name VARCHAR(255) NOT NULL, " +
            " string_value VARCHAR(255) NOT NULL, " +
            " string_type VARCHAR(255) NOT NULL, " +
            " FOREIGN KEY(hash_table_name) REFERENCES TABLE_NAME(hash_table_name)," +
            " UNIQUE (hash_table_name, string_value));";

    private final String UNIQUE_VALUES_SCRIPT = "CREATE TABLE UNIQUE_VALUES " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " hash_table_name VARCHAR(255), " +
            " string_value VARCHAR(255), " +
            " FOREIGN KEY(hash_table_name, string_value) REFERENCES TYPE_AND_VALUE(hash_table_name, string_value), " +
            " UNIQUE (hash_table_name, string_value));";

    private final String SCHEDULER_SCRIPT = "CREATE TABLE SCHEDULER " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " hash_table_name VARCHAR(255) UNIQUE NOT NULL, " +
            " date_value VARCHAR(255), " +
            " period_value VARCHAR(255) NOT NULL, " +
            " remainder INTEGER," +
            " FOREIGN KEY(hash_table_name) REFERENCES TABLE_NAME(hash_table_name));";

    public String getSCHEDULER_SCRIPT() {
        return SCHEDULER_SCRIPT;
    }

    public String getTABLE_NAME_SCRIPT() {
        return TABLE_NAME_SCRIPT;
    }

    public String getTYPE_AND_VALUE_SCRIPT() {
        return TYPE_AND_VALUE_SCRIPT;
    }

    public String getUNIQUE_VALUES_SCRIPT() {
        return UNIQUE_VALUES_SCRIPT;
    }
}
