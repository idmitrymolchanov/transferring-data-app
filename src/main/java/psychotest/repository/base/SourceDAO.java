package psychotest.repository.base;

import java.util.List;

public interface SourceDAO {
    List<List<String>> findAllSinceLastTargetActivity(int lastRow, List<String> values, String tableName);
    Integer getNumberLastString(String tableName, String uniqueValue, String uniqueString, List<String> list);

}
