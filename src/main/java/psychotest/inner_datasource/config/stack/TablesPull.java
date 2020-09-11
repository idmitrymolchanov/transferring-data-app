package psychotest.inner_datasource.config.stack;

public final class TablesPull implements Pull {
    private static TablesPull instance;
    private Stack stack;
    private int size;

    private TablesPull() {
        size = 2;
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

    public static TablesPull getInstance() {
        if (instance == null) {
            instance = new TablesPull();
        }
        return instance;
    }
}