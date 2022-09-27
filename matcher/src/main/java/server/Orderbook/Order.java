package server.Orderbook;

import javax.validation.constraints.*;

public class Order implements Comparable<Order> {
    @Min(0)
    final public double price;
    @Pattern(regexp = "^(buy|sell)$", message = "Order must be either buy or sell")
    final public String action;
    final public long time;
    //    final String id; needs to be randomly generated
    @NotNull(message = "account can not be null")
    @NotBlank(message = "account can not be blank")
    final private String account;
    @Min(0)
    public double quantity;
    @NotNull(message = "coin can not be null")
    @NotBlank(message = "coin can not be blank")
    @Pattern(regexp = "^(BTC|ETH|DOGE)$", message = "invalid or unsupported coin")
    public String coin = "BTC";


    public Order(String account, double price, double quantity, String action, long time, String coin) {
        this.account = account;
        this.price = price;
        this.quantity = quantity;
        this.action = action;
        this.time = time;
        this.coin = coin;
    }

    public Order clone() {
        return new Order(this.account, this.price, this.quantity, this.action, this.time, this.coin);
    }

    public String getAccount() {
        return this.account;
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
    public int compareTo(Order otherOrder) {
        return Double.compare(price, otherOrder.price);
    }

    @Override
    public String toString() {
        return "Order{" +
                "account=" + account +
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
        if (order.price == this.price && order.quantity == this.quantity && order.action.equals(this.action) && order.account.equals(this.account) && order.time == this.time) {
            return true;
        } else {
            return false;
        }
    }
}
