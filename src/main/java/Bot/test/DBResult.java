package Bot.test;

public class DBResult {
    private String order;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public DBResult(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "DBResult{" +
                "order='" + order + '\'' +
                '}';
    }

    public DBResult() {
    }

}
