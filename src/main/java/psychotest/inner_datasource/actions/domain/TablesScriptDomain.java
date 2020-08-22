package psychotest.inner_datasource.actions.domain;

public class TablesScriptDomain {
    private final String TABLE_NAME_SCRIPT = "CREATE TABLE TABLE_NAME " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " name_table VARCHAR(255) UNIQUE);";

    private final String TYPE_AND_VALUE_SCRIPT = "CREATE TABLE TYPE_AND_VALUE " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " string_table_name VARCHAR(255), " +
            " string_value VARCHAR(255), " +
            " string_type VARCHAR(255), " +
            " FOREIGN KEY(string_table_name) REFERENCES TABLE_NAME(name_table)," +
            " UNIQUE (string_table_name, string_value));";

    private final String UNIQ_AND_NOT_NULL_SCRIPT = "CREATE TABLE UNIQ_VALUES " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " string_table_name VARCHAR(255), " +
            " string_value VARCHAR(255), " +
            " unique_value INTEGER, " +
            "FOREIGN KEY(string_table_name, string_value) REFERENCES TYPE_AND_VALUE(string_table_name, string_value));";

    private final String SCHEDULER_SCRIPT = "CREATE TABLE SCHEDULER " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " string_table_name VARCHAR(255), " +
            " date_value VARCHAR(255), " +
            " period_value VARCHAR(255), " +
            " FOREIGN KEY(string_table_name) REFERENCES TABLE_NAME(name_table));";

    public String getSCHEDULER_SCRIPT() {
        return SCHEDULER_SCRIPT;
    }

    public String getTABLE_NAME_SCRIPT() {
        return TABLE_NAME_SCRIPT;
    }

    public String getTYPE_AND_VALUE_SCRIPT() {
        return TYPE_AND_VALUE_SCRIPT;
    }

    public String getUNIQ_AND_NOT_NULL_SCRIPT() {
        return UNIQ_AND_NOT_NULL_SCRIPT;
    }
}
