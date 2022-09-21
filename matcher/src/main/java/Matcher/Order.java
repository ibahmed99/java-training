package Matcher;

public class Order {
    final double price;
    final String action;
    final int time;
    //    final String id; needs to be randomly generated
    final private String account;
    double quantity;


    public Order(String account, double price, double quantity, String action, int time) {
        this.account = account;
        this.price = price;
        this.quantity = quantity;
        this.action = action;
        this.time = time;
    }

    public String getAccount() {
        return this.account;
    }

}
