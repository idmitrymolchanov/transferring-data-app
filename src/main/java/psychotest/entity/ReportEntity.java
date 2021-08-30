package psychotest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReportEntity {
    private Integer id;
    private String source_url;
    private String target_url;
    private String table_name;
    private String date_time;
    private String result;
}
