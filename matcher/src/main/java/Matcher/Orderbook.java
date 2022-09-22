package Matcher;

import java.util.ArrayList;
import java.util.Collections;

public class Orderbook {
    public ArrayList<Order> buys = new ArrayList<Order>();
    public ArrayList<Order> sells = new ArrayList<Order>();

    public ArrayList<Order> getPrivateSellbook() {
        this.sortLists();
        return this.sells;
    }

    public ArrayList<Order> getPrivateBuybook() {
        this.sortLists();
        return this.buys;
    }

    public ArrayList<Order> getSellbook() {
        this.sortLists();
        return this.sells;
    }

    public ArrayList<Order> getBuybook() {
        this.sortLists();
        return this.buys;
    }

    public void sortLists() {
        Collections.sort(this.buys);
        Collections.sort(this.sells);
        Collections.reverse(this.sells);
    }
}
