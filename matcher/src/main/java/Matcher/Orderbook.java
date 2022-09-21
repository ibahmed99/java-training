package Matcher;

import java.util.ArrayList;

public class Orderbook {
    ArrayList<Order> buys = new ArrayList<Order>();
    ArrayList<Order> sells = new ArrayList<Order>();

    public ArrayList<Order> getPrivateSellbook() {
        return this.sells;
    }

    public ArrayList<Order> getPrivateBuybook() {
        return this.buys;
    }
}
