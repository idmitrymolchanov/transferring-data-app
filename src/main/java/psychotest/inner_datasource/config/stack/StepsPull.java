package psychotest.inner_datasource.config.stack;

public final class StepsPull implements Pull {
    private static StepsPull instance;
    private Stack stack;
    private int size;

    private StepsPull() {
        size = 5;
        stack = new Stack(size);
    }

    @Override
    public boolean push(String data) {
        if(!stack.isFull()) {
            stack.push(data);
            return true;
        }
        return false;
    }

    @Override
    public String pop() {
        if(!stack.isEmpty()) {
            return stack.pop();
        }
        return "false";
    }

    @Override
    public String peek() {
        if(!stack.isEmpty()) {
            return stack.peek();
        }
        return "false";
    }

    public static StepsPull getInstance() {
        if (instance == null) {
            instance = new StepsPull();
        }
        return instance;
    }
}
