package psychotest.repository.base;

import java.util.List;

public interface TargetDAO {
    void saveAll(final List<List<String>> employeeList, List<String> types, List<String> fields, String tableName);
    String getLastValue(String tableName, String uniqueValue);

    void refreshCustomJdbc();
}
