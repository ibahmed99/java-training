package server.Matcher;

public class Trade implements Comparable<Trade> {
    
    double quantity;
    double price;
    long time;
    String coin;

    public Trade(String coin) {
        this.coin = coin;
        this.time = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "Trade{" +
                "quantity=" + quantity +
                ", price=" + price +
                ", time=" + time +
                ", coin='" + coin + '\'' +
                '}';
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getTime() {
        return time;
    }

    public String getCoin() {
        return coin;
    }

    @Override
    public int compareTo(Trade otherTrade) {
        return Long.compare(time, otherTrade.time);
    }
}
