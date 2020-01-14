package psychotest.database2.entity;

public class SberTest2{

    private Long id;
    private String EXTID_BCKGR;
    private String extid_USER;
    private String TABNUM;
    private String CHANGE_DATE;
    private String EXTID_PROGRAM;
    private String NAME_PROGRAM;
    private String SCALE;
    private String END_DATE_SCORE;
    private String NAME_SCORE;
    private String START_DATE_SCORE;
    private String EXTID_TEST;
    private String NAME_TEST;
    private Double RESULT_SCORE_NUM;

    public SberTest2(){}

    public SberTest2(Long id, String EXTID_BCKGR, String extid_USER, String TABNUM, String CHANGE_DATE, String EXTID_PROGRAM, String NAME_PROGRAM, String SCALE, String END_DATE_SCORE, String NAME_SCORE, String START_DATE_SCORE, String EXTID_TEST, String NAME_TEST, Double RESULT_SCORE_NUM) {
        this.id = id;
        this.EXTID_BCKGR = EXTID_BCKGR;
        this.extid_USER = extid_USER;
        this.TABNUM = TABNUM;
        this.CHANGE_DATE = CHANGE_DATE;
        this.EXTID_PROGRAM = EXTID_PROGRAM;
        this.NAME_PROGRAM = NAME_PROGRAM;
        this.SCALE = SCALE;
        this.END_DATE_SCORE = END_DATE_SCORE;
        this.NAME_SCORE = NAME_SCORE;
        this.START_DATE_SCORE = START_DATE_SCORE;
        this.EXTID_TEST = EXTID_TEST;
        this.NAME_TEST = NAME_TEST;
        this.RESULT_SCORE_NUM = RESULT_SCORE_NUM;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEXTID_BCKGR() {
        return EXTID_BCKGR;
    }

    public void setEXTID_BCKGR(String EXTID_BCKGR) {
        this.EXTID_BCKGR = EXTID_BCKGR;
    }

    public String getExtid_USER() {
        return extid_USER;
    }

    public void setEXTID_USER(String extid_USER) {
        this.extid_USER = extid_USER;
    }

    public String getTABNUM() {
        return TABNUM;
    }

    public void setTABNUM(String TABNUM) {
        this.TABNUM = TABNUM;
    }

    public String getCHANGE_DATE() {
        return CHANGE_DATE;
    }

    public void setCHANGE_DATE(String CHANGE_DATE) {
        this.CHANGE_DATE = CHANGE_DATE;
    }

    public String getEXTID_PROGRAM() {
        return EXTID_PROGRAM;
    }

    public void setEXTID_PROGRAM(String EXTID_PROGRAM) {
        this.EXTID_PROGRAM = EXTID_PROGRAM;
    }

    public String getNAME_PROGRAM() {
        return NAME_PROGRAM;
    }

    public void setNAME_PROGRAM(String NAME_PROGRAM) {
        this.NAME_PROGRAM = NAME_PROGRAM;
    }

    public String getSCALE() {
        return SCALE;
    }

    public void setSCALE(String SCALE) {
        this.SCALE = SCALE;
    }

    public String getEND_DATE_SCORE() {
        return END_DATE_SCORE;
    }

    public void setEND_DATE_SCORE(String END_DATE_SCORE) {
        this.END_DATE_SCORE = END_DATE_SCORE;
    }

    public String getNAME_SCORE() {
        return NAME_SCORE;
    }

    public void setNAME_SCORE(String NAME_SCORE) {
        this.NAME_SCORE = NAME_SCORE;
    }

    public String getSTART_DATE_SCORE() {
        return START_DATE_SCORE;
    }

    public void setSTART_DATE_SCORE(String START_DATE_SCORE) {
        this.START_DATE_SCORE = START_DATE_SCORE;
    }

    public String getEXTID_TEST() {
        return EXTID_TEST;
    }

    public void setEXTID_TEST(String EXTID_TEST) {
        this.EXTID_TEST = EXTID_TEST;
    }

    public String getNAME_TEST() {
        return NAME_TEST;
    }

    public void setNAME_TEST(String NAME_TEST) {
        this.NAME_TEST = NAME_TEST;
    }

    public Double getRESULT_SCORE_NUM() {
        return RESULT_SCORE_NUM;
    }

    public void setRESULT_SCORE_NUM(Double RESULT_SCORE_NUM) {
        this.RESULT_SCORE_NUM = RESULT_SCORE_NUM;
    }
}
