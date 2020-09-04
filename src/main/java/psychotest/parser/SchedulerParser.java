package psychotest.parser;

public class SchedulerParser implements Parser {
    public Integer getCodeByValue(String value) {
        switch (value) {
            case "1 day":
                return 1;
            case "2 days":
                return 2;
            case "3 days":
                return 3;
            case"4 days":
                return 4;
            case "5 days":
                return 5;
            case "6 days":
                return 6;
            case"every week":
                return 7;
            case "28 days":
                return 28;
            default: return 0;
        }
    }
}
