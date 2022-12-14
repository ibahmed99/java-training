package server.Matcher;

import server.Orderbook.*;

import java.util.ArrayList;

public class Matcher {

    public static void matchNewOrder(Order newOrder, Orderbook existingOrders, ArrayList<Trade> trades) {
        existingOrders.sortLists();
        if (newOrder.action.equals("buy")) {
            processNewBuy(newOrder, existingOrders, trades);
        } else {
            processNewSell(newOrder, existingOrders, trades);
        }

        removeZeroQuantOrders(existingOrders);
        existingOrders.sortLists();
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
        ArrayList<MarketUpdate> marketUpdates = pairNewSell(newOrder, existingOrders, trades);
        if (marketUpdates.size() == 0) {
            existingOrders.sells.add(newOrder);
        } else {
            updateBuyMarket(marketUpdates, existingOrders);
        }
    }

    private static ArrayList<MarketUpdate> pairNewBuy(Order newOrder, Orderbook existingOrders, ArrayList<Trade> trades) {
        ArrayList<MarketUpdate> marketUpdates = new ArrayList<MarketUpdate>();
        ArrayList<Order> existingSells = existingOrders.sells;

        for (Order existingSell : existingSells) {

            boolean buyHigherThanSell = newOrder.price >= existingSell.price;
            boolean notSameAccount = !(newOrder.getAccount().equals(existingSell.getAccount()));
            boolean notSameCoin = newOrder.getCoin().equals(existingSell.getCoin());

            if (buyHigherThanSell && notSameAccount && notSameCoin) {
                MarketUpdate update = newTrade(newOrder, existingSell, trades);
                if (update.deleteOldOrder) newOrder = update.newOrder;
                marketUpdates.add(update);
                if (!update.deleteOldOrder) return marketUpdates;
            }
        }
        return marketUpdates;
    }

    private static ArrayList<MarketUpdate> pairNewSell(Order newOrder, Orderbook existingOrders, ArrayList<Trade> trades) {
        ArrayList<MarketUpdate> marketUpdates = new ArrayList<MarketUpdate>();
        ArrayList<Order> existingBuys = existingOrders.buys;
        for (Order existingBuy : existingBuys) {
            boolean buyHigherThanSell = newOrder.price <= existingBuy.price;
            boolean notSameAccount = !(newOrder.getAccount().equals(existingBuy.getAccount()));
            boolean sameCoin = newOrder.coin.equals(existingBuy.coin);

            if (buyHigherThanSell && notSameAccount && sameCoin) {
                MarketUpdate update = newTrade(newOrder, existingBuy, trades);

                if (update.deleteOldOrder) newOrder = update.newOrder;
                marketUpdates.add(update);

                if (!update.deleteOldOrder) return marketUpdates;
            }
        }
        return marketUpdates;
    }

    private static MarketUpdate newTrade(Order newOrder, Order existingOrder, ArrayList<Trade> trades) {
        boolean newOrderLargerQuant = newOrder.quantity > existingOrder.quantity;
        boolean existingOrderLargerQuant = newOrder.quantity < existingOrder.quantity;
        boolean sameQuant = newOrder.quantity == existingOrder.quantity;
        String coin = newOrder.coin;

        Trade trade = new Trade(coin);

        if (newOrder.action.equals("buy")) trade.setPrice(newOrder.price);
        else trade.setPrice(existingOrder.price);

        if (newOrderLargerQuant) {
            trade.setQuantity(existingOrder.quantity);
            trades.add(trade);
            Order order = newOrder.clone();
            order.quantity = newOrder.quantity - existingOrder.quantity;
            return new MarketUpdate(true, existingOrder, order);
        } else /* if (existingOrderLargerQuant || sameQuant) */ {
            trade.setQuantity(newOrder.quantity);
            trades.add(trade);
            Order order = existingOrder.clone();
            order.quantity = existingOrder.quantity - newOrder.quantity;
            return new MarketUpdate(false, existingOrder, order);
        }

//        return new MarketUpdate();
    }

    private static void updateSellMarket(ArrayList<MarketUpdate> marketUpdates, Orderbook existingOrders) {
        for (int i = 0; i < marketUpdates.size(); i++) {
            updateMarket(marketUpdates.get(i), existingOrders.sells);
            if (i == marketUpdates.size() - 1 && marketUpdates.get(i).deleteOldOrder) {
                existingOrders.buys.add(marketUpdates.get(i).newOrder);
            }
        }
    }

    private static void updateBuyMarket(ArrayList<MarketUpdate> marketUpdates, Orderbook existingOrders) {
        for (int i = 0; i < marketUpdates.size(); i++) {
            updateMarket(marketUpdates.get(i), existingOrders.buys);
            if (i == marketUpdates.size() - 1 && marketUpdates.get(i).deleteOldOrder) {
                existingOrders.sells.add(marketUpdates.get(i).newOrder);
            }
        }
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

    public static void removeZeroQuantOrders(Orderbook existingOrders) {
        // remove orders with zero quantity
        existingOrders.sells.removeIf(order -> order.quantity == 0);
        existingOrders.buys.removeIf(order -> order.quantity == 0);
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the matcher");
    }

}
