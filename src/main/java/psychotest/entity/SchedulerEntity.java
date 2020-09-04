package psychotest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SchedulerEntity {
    private Long id;
    private String hashTableName;
    private String dateValue;
    private String periodValue;
    private Long remainder;
}
