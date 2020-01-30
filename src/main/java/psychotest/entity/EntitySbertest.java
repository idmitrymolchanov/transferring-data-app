package psychotest.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class EntitySbertest{

    private Long id;
    private String extidBckgr;
    private String extidUser;
    private String tabnum;
    private String changeDate;
    private String extidProgram;
    private String nameProgram;
    private String scale;
    private String endDateScore;
    private String nameScore;
    private String startDateScore;
    private String extidTest;
    private String nameTest;
    private Double resultScoreNum;

}