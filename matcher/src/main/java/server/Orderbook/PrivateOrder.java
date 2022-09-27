package server.Orderbook;

public class PrivateOrder implements Comparable<PrivateOrder> {
    final public double price;
    final public String action;
    final public long time;
    //    final String id; needs to be randomly generated
    public double quantity;
    public String coin = "BTC";

    public PrivateOrder(Order order) {
        this.price = order.price;
        this.quantity = order.quantity;
        this.action = order.action;
        this.time = order.time;
        this.coin = order.coin;
    }

    public double getPrice() {
        return this.price;
    }

    public double getQuantity() {
        return this.quantity;
    }

    public String getAction() {
        return this.action;
    }

    public long getTime() {
        return this.time;
    }

    public String getCoin() {
        return this.coin;
    }

    @Override
    public int compareTo(PrivateOrder otherOrder) {
        return Double.compare(price, otherOrder.price);
    }

    @Override
    public String toString() {
        return "PrivateOrder{" +
                "price=" + price +
                ", action='" + action + '\'' +
                ", time=" + time +
                ", quantity=" + quantity +
                ", coin='" + coin + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        Order order = (Order) obj;
        if (order.price == this.price && order.quantity == this.quantity && order.action.equals(this.action) && order.time == this.time) {
            return true;
        } else {
            return false;
        }
    }
}
