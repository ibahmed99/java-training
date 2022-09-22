package Matcher;

public class MarketUpdate {
    boolean deleteOldOrder;
    Order oldOrder;
    Order newOrder;

    public MarketUpdate(boolean deleteOldOrder, Order oldOrder, Order newOrder) {
        this.deleteOldOrder = deleteOldOrder;
        this.oldOrder = oldOrder;
        this.newOrder = newOrder;
    }
}
