package psychotest.inner_datasource.config.stack;

public interface Pull {

    boolean push(String data);
    String pop();
    String peek();
}
