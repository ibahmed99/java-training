package Matcher;

import java.util.ArrayList;

public class Matcher {

    public Matcher(Order newOrder) {

    }

    public static void matchNewOrder(Order newOrder, Orderbook existingOrders, ArrayList<Trade> trades) {
        if (newOrder.action.equals("buy")) {
            processNewBuy(newOrder, existingOrders, trades);
        } else {
            processNewSell(newOrder, existingOrders, trades);
        }

        removeZeroQuantOrders(existingOrders);
    }

    private static void processNewBuy(Order newOrder, Orderbook existingOrders, ArrayList<Trade> trades) {
        ArrayList<MarketUpdate> marketUpdates = pairNewBuy(newOrder, existingOrders, trades);
        if (marketUpdates.size() == 0) {
            existingOrders.buys.add(newOrder);
        } else {
            updateSellMarket(marketUpdates, existingOrders);
        }
    }

    private static void processNewSell(Order newOrder, Orderbook existingOrders, ArrayList<Trade> trades) {

    }

    private static ArrayList<MarketUpdate> pairNewBuy(Order newOrder, Orderbook existingOrders, ArrayList<Trade> trades) {
        ArrayList<MarketUpdate> marketUpdates = new ArrayList<MarketUpdate>();
        ArrayList<Order> existingSells = existingOrders.getSellbook();

        for (Order existingSell : existingSells) {

            boolean buyHigherThanSell = newOrder.price >= existingSell.price;
            boolean notSameAccount = !(newOrder.getAccount().equals(existingSell.getAccount()));
            boolean notSameCoin = !(newOrder.coin.equals(existingSell.coin));

            if (buyHigherThanSell && notSameAccount && notSameCoin) {
                MarketUpdate update = newTrade(newOrder, existingSell, trades);
                if (update.deleteOldOrder) newOrder = update.newOrder;
                marketUpdates.add(update);
                if (!update.deleteOldOrder) return marketUpdates;
            }
        }
        return marketUpdates;
    }

    private static void pairNewSell(Order newOrder, Orderbook existingOrders) {

    }

    private static MarketUpdate newTrade(Order newOrder, Order existingOrder, ArrayList<Trade> trades) {
        boolean newOrderLargerQuant = newOrder.quantity > existingOrder.quantity;
        boolean existingOrderLargerQuant = newOrder.quantity < existingOrder.quantity;
        boolean sameQuant = newOrder.quantity == existingOrder.quantity;
        String coin = newOrder.coin;

        Trade trade = new Trade(coin);

        if (newOrder.action.equals("buy")) trade.price = newOrder.price;
        else trade.price = existingOrder.price;

        if (newOrderLargerQuant) {
            trade.quantity = existingOrder.quantity;
            trades.add(trade);
            Order order = newOrder.clone();
            order.quantity = newOrder.quantity - existingOrder.quantity;
            return new MarketUpdate(true, existingOrder, order);
        } else /* if (existingOrderLargerQuant || sameQuant) */ {
            trade.quantity = newOrder.quantity;
            trades.add(trade);
            Order order = existingOrder.clone();
            order.quantity = existingOrder.quantity - newOrder.quantity;
            return new MarketUpdate(false, existingOrder, order);
        }

//        return new MarketUpdate();
    }

    private static void updateSellMarket(ArrayList<MarketUpdate> marketUpdates, Orderbook existingOrders) {

    }

    private static void updateBuyMarket(ArrayList<MarketUpdate> marketUpdates, Orderbook existingOrders) {

    }

    private static void removeZeroQuantOrders(Orderbook existingOrders) {
        // remove orders with zero quantity
    }

    private static void updateMarket(MarketUpdate marketUpdate, ArrayList<Order> existingOrderList) {
        if (marketUpdate.deleteOldOrder) {
            deleteExistingMarketOrder(marketUpdate, existingOrderList);
        } else {
            updateExistingMarketOrder(marketUpdate, existingOrderList);
        }
    }

    private static void deleteExistingMarketOrder(MarketUpdate marketUpdate, ArrayList<Order> existingOrderList) {
        existingOrderList.remove(marketUpdate.oldOrder);
    }

    private static void updateExistingMarketOrder(MarketUpdate marketUpdate, ArrayList<Order> existingOrderList) {
        int orderIndex = existingOrderList.indexOf(marketUpdate.oldOrder);
        existingOrderList.set(orderIndex, marketUpdate.newOrder);
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the matcher");
    }

}
