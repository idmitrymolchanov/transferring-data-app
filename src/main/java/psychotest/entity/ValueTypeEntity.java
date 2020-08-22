package psychotest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValueTypeEntity {
    private Long id;
    private String StringTableName;
    private String StringValue;
    private String StringType;
}
