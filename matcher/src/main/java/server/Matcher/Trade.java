package server.Matcher;

public class Trade implements Comparable<Trade> {
    double quantity;
    double price;
    long time;
    String coin;
    String id;

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
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public int compareTo(Trade otherTrade) {
        return Long.compare(time, otherTrade.time);
    }
}
