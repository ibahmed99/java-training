package server.Matcher;

import org.springframework.stereotype.Component;
import server.Orderbook.*;

import java.util.ArrayList;

@Component
public class MatcherApplication {

    private Orderbook existingOrders;
    private ArrayList<Trade> trades;

    public MatcherApplication() {
        this.existingOrders = new Orderbook();
        this.trades = new ArrayList<Trade>();
    }

    public Orderbook getExistingOrders() {
        return this.existingOrders;
    }

    public ArrayList<Trade> getTrades() {
        return trades;
    }
}
