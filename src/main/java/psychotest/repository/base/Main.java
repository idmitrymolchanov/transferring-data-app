package psychotest.repository.base;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        String listString = String.join(", ", list);
        System.out.println(listString);
    }
}
