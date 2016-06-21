package assignment5;

import java.util.LinkedList;
import java.util.Queue;

public class Sales {

    public static void main(String args[]) {
        fifo();
    }

    public static void fifo() {
        Queue<Call> queue = new LinkedList<Call>();
        int time = 0;
        int profit = 0;
        Call currentCall = null;
        int currentCallTaken = 0;

        for (int i = 0; i < 10; i++) {
            queue.add(Call.randomCall(time));
        }
        currentCall = queue.remove();

        for (time = 0; time < 8 * 60; time++) {
            if (time - currentCallTaken == currentCall.getDuration()) {
                profit += currentCall.getProfit();

                do {
                    currentCall = queue.remove();
                } while (!queue.isEmpty() && time - currentCall.getTimestamp() > 60);
                currentCallTaken = time;

                queue.add(Call.randomCall(time));
                System.out.println("Time: " + time + "min, Profit: $" + profit + ", Queue: " + queue.size());
            }
        }

    }

    public static void remainingTime() {

    }

    public static void maxProfit() {

    }

}
