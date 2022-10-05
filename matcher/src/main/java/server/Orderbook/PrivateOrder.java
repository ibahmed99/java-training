package server.Orderbook;

public class PrivateOrder implements Comparable<PrivateOrder> {
    final public String action;
    final public long time;
    public double price;
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

    public PrivateOrder(String action, long time, double price, double quantity, String coin) {
        this.price = price;
        this.quantity = quantity;
        this.action = action;
        this.time = time;
        this.coin = coin;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
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
