package assignment5;

public class Call {

    // when the call first arrived
    private int timestamp;

    // how long the call will take
    private int duration;

    // how much money you will make
    private int profit;

    private Call(int timestamp, int duration, int profit) {
        this.timestamp = timestamp;
        this.duration = duration;
        this.profit = profit;
    }

    public static Call randomCall(int timestamp) {
        return new Call(timestamp, (int) (Math.random() * 15) + 1, (int) (Math.random() * 100) + 1);
    }

    public int getTimestamp() {
        return timestamp;
    }

    public int getDuration() {
        return duration;
    }

    public int getProfit() {
        return profit;
    }

}
