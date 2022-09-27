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

    public void processNewOrder(Order newOrder) {
        Matcher.matchNewOrder(newOrder, matcherApplication.getExistingOrders(), matcherApplication.getTrades());
    }
}
