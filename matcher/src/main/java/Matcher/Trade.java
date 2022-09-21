package Matcher;

public class Trade {
    double quantity;
    double price;
    int time;
    String coin;
    String id;

    public Trade(double quantity, double price, int time, String coin) {
        this.price = price;
        this.quantity = quantity;
        this.time = time;
    }
}
