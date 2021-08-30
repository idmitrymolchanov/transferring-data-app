package psychotest.inner_datasource.actions.domain;

public class ReportScriptDomain {

    private final String REPORT_SCRIPT = "CREATE TABLE REPORT_TABLE " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " source_url VARCHAR(255), " +
            " target_url VARCHAR(255), " +
            " table_name VARCHAR(255), " +
            " date_time VARCHAR(255), " +
            " result VARCHAR(255)," +
            " UNIQUE (source_url, target_url, table_name), " +
            " FOREIGN KEY(source_url) REFERENCES DATASOURCE_CONNECTIONS(source_url), " +
            " FOREIGN KEY(target_url) REFERENCES DATASOURCE_CONNECTIONS(target_url), " +
            " FOREIGN KEY(table_name) REFERENCES TABLE_NAME(name_table));";

    public String getREPORT_SCRIPT() {
        return REPORT_SCRIPT;
    }
}
