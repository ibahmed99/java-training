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
        this.trades = new ArrayList<>();
    }

    public Orderbook getExistingOrders() {
        return this.existingOrders;
    }

    public PrivateOrderbook getPrivateExistingOrders() {
        return this.existingOrders.privatiseOrderbook();
    }

    public ArrayList<Trade> getTrades() {
        return this.trades;
    }
}
