package psychotest.inner_datasource.config.stack;

public class Stack {
    private String[] arr;
    private int top;
    private int size;

    public Stack(int size) {
        this.size = size;
        arr = new String[size];
        top = -1;
    }

    public void push(String data) {
        arr[++top] = data;
    }

    public String  pop() {
        return arr[top--];
    }

    public String  peek() {
        return arr[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == size - 1;
    }

}
