package psychotest.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class SchedulerEntity {

    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private String date;
}