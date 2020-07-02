package psychotest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DatasourceEntity {
    private Long id;
    private String driver_name;
    private String url;
    private String username;
    private String password;
}
