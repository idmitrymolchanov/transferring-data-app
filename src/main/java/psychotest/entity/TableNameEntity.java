package psychotest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableNameEntity {
    private Long id;
    private String tableName;
    private String hash_connection;
    private String hash_table_name;
}
