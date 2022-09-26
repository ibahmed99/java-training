package server.Matcher;

import server.Matcher.*;
import server.Orderbook.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MatcherTest {
    ArrayList<Trade> trades;
    private Orderbook existingOrders;
    private Order sellOrder1;
    private Order sellOrder2;
    private Order buyOrder1;
    private long time;
    private Trade trade1;

    @BeforeEach
    void setUp() {
        time = System.currentTimeMillis();

        existingOrders = new Orderbook();
        sellOrder1 = new Order("1", 10, 20, "sell", time, "BTC");
        sellOrder2 = new Order("3", 30, 10, "sell", time, "BTC");
        buyOrder1 = new Order("2", 20, 5, "buy", time, "BTC");

        existingOrders.addOrder(sellOrder1);
        existingOrders.addOrder(sellOrder2);
        existingOrders.addOrder(buyOrder1);

        trade1 = new Trade("BTC");
        trade1.price = 25;
        trade1.quantity = 10;
        trades = new ArrayList<Trade>();
        trades.add(trade1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void matcherWorksWithSellOrder() {

        Order newOrder = new Order("4", 15, 15, "sell", time, "BTC");

        Matcher.matchNewOrder(newOrder, existingOrders, trades);


        Order newSellOrder3 = new Order("4", 15, 10, "sell", time, "BTC");
        Orderbook newExistingOrders = new Orderbook();
        newExistingOrders.addOrder(sellOrder1);
        newExistingOrders.addOrder(sellOrder2);
        newExistingOrders.addOrder(newSellOrder3);

        assertEquals(existingOrders, newExistingOrders);
    }

    @Test
    void matcherWorksWithBuyOrder() {
        Order newOrder = new Order("4", 25, 10, "buy", time, "BTC");

        Matcher.matchNewOrder(newOrder, existingOrders, trades);

        Order newSellOrder1 = new Order("1", 10, 10, "sell", time, "BTC");
        Orderbook newExistingOrders = new Orderbook();
        newExistingOrders.addOrder(newSellOrder1);
        newExistingOrders.addOrder(sellOrder2);
        newExistingOrders.addOrder(buyOrder1);

        assertEquals(existingOrders, newExistingOrders);
    }

    @Test
    void buyOrderWithSameQuantityAsExistingSellOrderFulfills() {
        Order newOrder = new Order("4", 15, 20, "buy", time, "BTC");

        Matcher.matchNewOrder(newOrder, existingOrders, trades);

        Orderbook newExistingOrders = new Orderbook();
        newExistingOrders.addOrder(sellOrder2);
        newExistingOrders.addOrder(buyOrder1);

        assertEquals(existingOrders, newExistingOrders);
    }

    @Test
    void sellOrderWithSameQuantityAsExistingBuyOrderFulfills() {
        long time2 = System.currentTimeMillis();

        Order newOrder = new Order("4", 15, 5, "sell", time2, "BTC");

        Matcher.matchNewOrder(newOrder, existingOrders, trades);

        Orderbook newExistingOrders = new Orderbook();
        newExistingOrders.addOrder(sellOrder1);
        newExistingOrders.addOrder(sellOrder2);

        assertEquals(existingOrders, newExistingOrders);
    }

    @Test
    void buyOrderTradesWithMultipleSellOrdersDoesNotFulfill() {
        long time2 = System.currentTimeMillis();

        Order newOrder = new Order("4", 35, 35, "buy", time2, "BTC");

        Matcher.matchNewOrder(newOrder, existingOrders, trades);

        Order buyOrder2 = new Order("4", 35, 5, "buy", time2, "BTC");

        Orderbook newExistingOrders = new Orderbook();
        newExistingOrders.addOrder(buyOrder1);
        newExistingOrders.addOrder(buyOrder2);
//        System.out.println(existingOrders.sells);
//        System.out.println(existingOrders.buys);
//        System.out.println(newExistingOrders.sells);
//        System.out.println(newExistingOrders.buys);

        assertEquals(existingOrders, newExistingOrders);
    }

    @Test
    void sellOrderTradesWithMultipleBuyOrdersFulfills() {
        long time2 = System.currentTimeMillis();

        existingOrders = new Orderbook();
        existingOrders.addOrder(sellOrder1);
        existingOrders.addOrder(buyOrder1);

        Order buyOrder2 = new Order("3", 30, 10, "buy", time, "BTC");
        existingOrders.addOrder(buyOrder2);


        Order newOrder = new Order("4", 15, 12, "sell", time2, "BTC");
        existingOrders.sortLists();

        Matcher.matchNewOrder(newOrder, existingOrders, trades);

        Orderbook newExistingOrders = new Orderbook();
        newExistingOrders.addOrder(sellOrder1);
        newExistingOrders.addOrder(new Order("2", 20, 3, "buy", time, "BTC"));

        assertEquals(existingOrders, newExistingOrders);
    }

    @Test
    void buyOrderTradesWithMultipleSellOrdersFulfills() {
        long time2 = System.currentTimeMillis();

        Order newOrder = new Order("4", 35, 25, "buy", time2, "BTC");

        Matcher.matchNewOrder(newOrder, existingOrders, trades);

        Orderbook newExistingOrders = new Orderbook();
        newExistingOrders.addOrder(new Order("3", 30, 5, "sell", time, "BTC"));
        newExistingOrders.addOrder(new Order("2", 20, 5, "buy", time, "BTC"));

        assertEquals(existingOrders, newExistingOrders);
    }

    @Test
    void sellOrderTradesWithMultipleBuyOrdersDoesNotFulfill() {
        long time2 = System.currentTimeMillis();

        Order newOrder = new Order("4", 15, 20, "sell", time2, "BTC");

        Orderbook existingOrders = new Orderbook();
        existingOrders.addOrder(sellOrder1);
        existingOrders.addOrder(buyOrder1);
        existingOrders.addOrder(new Order("3", 30, 10, "buy", time, "BTC"));

        Matcher.matchNewOrder(newOrder, existingOrders, trades);

        Orderbook newExistingOrders = new Orderbook();
        newExistingOrders.addOrder(sellOrder1);
        newExistingOrders.addOrder(new Order("4", 15, 5, "sell", time2, "BTC"));

        assertEquals(existingOrders, newExistingOrders);
    }

    @Test
    void removingZeroQuantBuyOrder() {

        existingOrders.addOrder(new Order("4", 20, 0, "buy", time, "BTC"));

        Matcher.removeZeroQuantOrders(existingOrders);

        Orderbook newExistingOrders = new Orderbook();

        newExistingOrders.addOrder(sellOrder1);
        newExistingOrders.addOrder(sellOrder2);
        newExistingOrders.addOrder(buyOrder1);

        assertEquals(existingOrders, newExistingOrders);
    }

    @Test
    void removingZeroQuantSellOrder() {

        existingOrders.addOrder(new Order("4", 20, 0, "sell", time, "BTC"));

        Matcher.removeZeroQuantOrders(existingOrders);

        Orderbook newExistingOrders = new Orderbook();

        newExistingOrders.addOrder(sellOrder1);
        newExistingOrders.addOrder(sellOrder2);
        newExistingOrders.addOrder(buyOrder1);

        assertEquals(existingOrders, newExistingOrders);
    }
}