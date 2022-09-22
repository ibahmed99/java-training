package Matcher;

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
    public int compareTo(Trade otherTrade) {
        return Long.compare(time, otherTrade.time);
    }
}
