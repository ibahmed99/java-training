package server.Orderbook;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.Matcher.*;

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
}
