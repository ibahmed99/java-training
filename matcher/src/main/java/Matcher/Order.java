package Matcher;

public class Order implements Comparable<Order> {
    final double price;
    final String action;
    final long time;
    //    final String id; needs to be randomly generated
    final private String account;
    double quantity;
    String coin = "BTC";


    public Order(String account, double price, double quantity, String action, long time, String coin) {
        this.account = account;
        this.price = price;
        this.quantity = quantity;
        this.action = action;
        this.time = time;
        this.coin = coin;
    }

    public Order clone() {
        return new Order(this.account, this.price, this.quantity, this.action, this.time, this.coin);
    }

    public String getAccount() {
        return this.account;
    }

    @Override
    public int compareTo(Order otherOrder) {
        return Double.compare(price, otherOrder.price);
    }
}
