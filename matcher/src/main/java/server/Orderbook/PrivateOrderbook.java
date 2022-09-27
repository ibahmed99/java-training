package server.Orderbook;

import java.util.ArrayList;
import java.util.Collections;

public class PrivateOrderbook {
    public ArrayList<PrivateOrder> buys = new ArrayList<PrivateOrder>();
    public ArrayList<PrivateOrder> sells = new ArrayList<PrivateOrder>();

    public PrivateOrderbook(ArrayList<Order> existingSells, ArrayList<Order> existingBuys) {
        for (Order existingSell : existingSells) {
            PrivateOrder privateSell = new PrivateOrder(existingSell);
            this.sells.add(privateSell);
        }
        for (Order existingBuy : existingBuys) {
            PrivateOrder privateBuy = new PrivateOrder(existingBuy);
            this.buys.add(privateBuy);
        }
    }

    public void sortLists() {
        Collections.sort(this.buys);
        Collections.sort(this.sells);
        Collections.reverse(this.buys);
    }
}
