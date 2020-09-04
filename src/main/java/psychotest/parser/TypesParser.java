package psychotest.parser;

public class TypesParser implements Parser {
    public Integer getCodeByValue(String value) {
        switch (value) {
            case "CHAR":
            case "VARCHAR":
            case "TEXT":
                return 1;
            case"BOOL":
                return 3;
            case "INT":
            case"INT UNSIGNED":
                return 2;
            case "BIGINT":
            case"BIGINT UNSIGNED":
                return 8;
            case "DECIMAL":
                return 81;
            case"FLOAT":
                return 7;
            case "DOUBLE":
                return 6;
            case"DATE":
            case"DATETIME":
                return 5;
            case "TIME":
                return 9;
            default: return 0;
        }
    }
}
