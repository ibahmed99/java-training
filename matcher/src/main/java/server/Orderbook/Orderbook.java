package server.Orderbook;

import java.util.ArrayList;
import java.util.Collections;

public class Orderbook {
    public ArrayList<Order> buys = new ArrayList<Order>();
    public ArrayList<Order> sells = new ArrayList<Order>();

    public void sortLists() {
        Collections.sort(this.buys);
        Collections.sort(this.sells);
        Collections.reverse(this.buys);
    }

    public void addOrder(Order order) {
        if (order.action.equals("buy")) {
            this.buys.add(order);
        } else {
            this.sells.add(order);
        }
        this.sortLists();
        return;
    }

    public PrivateOrderbook privatiseOrderbook() {
        PrivateOrderbook privateOrderbook = new PrivateOrderbook(this.sells, this.buys);
        privateOrderbook.sortLists();
        return privateOrderbook;
    }

    @Override
    public boolean equals(Object obj) {
        Orderbook orderbook = (Orderbook) obj;
        if (this.buys.size() != orderbook.buys.size() || this.sells.size() != orderbook.sells.size()) return false;
        for (int i = 0; i < orderbook.buys.size(); i++) {
            if (!(this.buys.get(i).equals(orderbook.buys.get(i)))) return false;
        }
        for (int i = 0; i < orderbook.sells.size(); i++) {
            if (!(this.sells.get(i).equals(orderbook.sells.get(i)))) return false;
        }
        return true;
    }
}
