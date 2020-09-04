package psychotest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UniqueValuesEntity {
    private Long id;
    private String hashTableName;
    private String stringValue;
}
