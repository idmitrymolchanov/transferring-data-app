package psychotest.stackTest;

import org.junit.Test;
import psychotest.inner_datasource.config.stack.Pull;
import psychotest.inner_datasource.config.stack.TablesPull;

public class StackTest {
    private Pull pull = TablesPull.getInstance();

    @Test
    public void test() {
        pull.push("rrr");
        pull.push("fff");
        System.out.println(pull.peek());
        System.out.println(pull.pop());
        System.out.println(pull.peek());
        System.out.println(pull.peek());
    }

}
