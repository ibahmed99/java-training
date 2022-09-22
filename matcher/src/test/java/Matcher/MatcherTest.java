package Matcher;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MatcherTest {

    private Orderbook existingOrders;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void matcherTest1() {
        long time = System.currentTimeMillis();
        Order sellOrder1 = new Order("1", 10, 20, "sell", time, "BTC");
        Order sellOrder2 = new Order("3", 30, 10, "sell", time, "BTC");
        Order buyOrder1 = new Order("2", 20, 5, "buy", time, "BTC");
        existingOrders = new Orderbook();
        existingOrders.addOrder(sellOrder1);
        existingOrders.addOrder(sellOrder2);
        existingOrders.addOrder(buyOrder1);

        Trade trade1 = new Trade("BTC");
        trade1.price = 25;
        trade1.quantity = 10;
        ArrayList<Trade> trades = new ArrayList<Trade>();
        trades.add(trade1);

        Order newOrder = new Order("4", 15, 15, "sell", time, "BTC");

        Matcher.matchNewOrder(newOrder, existingOrders, trades);


        Order newSellOrder3 = new Order("4", 15, 10, "sell", time, "BTC");
        Orderbook newExistingOrders = new Orderbook();
        newExistingOrders.addOrder(sellOrder1);
        newExistingOrders.addOrder(sellOrder2);
        newExistingOrders.addOrder(newSellOrder3);

        assertEquals(existingOrders.buys.size(), newExistingOrders.buys.size());
        assertEquals(existingOrders.sells.size(), newExistingOrders.sells.size());


        for (int i = 0; i < existingOrders.buys.size(); i++) {
            assertThat(existingOrders.buys.get(i)).isEqualToComparingFieldByFieldRecursively(newExistingOrders.buys.get(i));
        }
        assertEquals(existingOrders.buys, newExistingOrders.buys);
        assertEquals(existingOrders.sells, newExistingOrders.sells);
    }
}