package server.Orderbook;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.Matcher.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.*;

@Service
public class OrderbookService {

    @Autowired
    private MatcherApplication matcherApplication;

    public PrivateOrderbook getPrivateOrderbook() {

        return matcherApplication.getPrivateExistingOrders();
    }

    public PrivateOrderbook specificUsersOrderbook(String user) {
        Orderbook existingOrdersCopy = matcherApplication.getExistingOrders().clone();
        existingOrdersCopy.sells.removeIf(order -> !order.getAccount().equals(user));
        existingOrdersCopy.buys.removeIf(order -> !order.getAccount().equals(user));
        return new PrivateOrderbook(existingOrdersCopy.sells, existingOrdersCopy.buys);
    }

    public void processNewOrder(Order newOrder) {
        Matcher.matchNewOrder(newOrder, matcherApplication.getExistingOrders(), matcherApplication.getTrades());
    }

    public PrivateOrderbook aggregateOrderbook(double value) {
        PrivateOrderbook privateOrderbook = matcherApplication.getPrivateExistingOrders();
        privateOrderbook.sells = this.aggregateList(privateOrderbook.sells, "sell", value, "BTC");
        privateOrderbook.buys = this.aggregateList(privateOrderbook.buys, "sell", value, "BTC");
        privateOrderbook.sortLists();
        return privateOrderbook;
    }

    public ArrayList<PrivateOrder> aggregateList(ArrayList<PrivateOrder> orders, String action, double aggregation, String coin) {
        orders.forEach((order) -> {
            double newPrice;
            if (order.price / aggregation == 0) newPrice = aggregation;
            else newPrice = Math.round(order.price / aggregation) * aggregation;
            order.setPrice(newPrice);
        });

        Map<Double, Double> map = new HashMap<>();
        for (PrivateOrder order : orders) {
            if (map.containsKey(order.price)) {
                double q = map.get(order.price) + order.quantity;
                map.put(order.price, q);
            } else {
                map.put(order.price, order.quantity);
            }
        }
        ArrayList<PrivateOrder> aggregatedList = new ArrayList<>();
        for (Map.Entry<Double, Double> pricePoint : map.entrySet()) {
            aggregatedList.add(new PrivateOrder(action, System.currentTimeMillis(), pricePoint.getKey(), pricePoint.getValue(), coin));
        }
        return aggregatedList;
    }
}
