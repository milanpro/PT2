package assignment5;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Sales {

	public static void main( String args[] ) {

		fifo();
		System.out.println();
		remainingTime();
		System.out.println();
		maxProfit();
	}

	public static void fifo() {
		Queue<Call> queue = new LinkedList<Call>();
		int time = 0;
		int profit = 0;
		Call currentCall = null;
		int currentCallTaken = 0;

		for ( int i = 0; i < 10; i++ ) {
			queue.add( Call.randomCall( time ) );
		}
		currentCall = queue.remove();

		for ( time = 0; time < 8*60; time++ ) {
			if ( time-currentCallTaken == currentCall.getDuration() ) {
				profit += currentCall.getProfit();

				do {
					currentCall = queue.remove();
				} while ( !queue.isEmpty() && time-currentCall.getTimestamp() > 60 );
				currentCallTaken = time;

				queue.add( Call.randomCall( time ) );
				System.out.println( "Time: " + time + "min, Profit: $" + profit + ", Queue: " + queue.size() );
			}
		}

	}

	public static void remainingTime() { // gleicher ablauf nur mit TimeComparator
		Comparator<Call> comparator = new TimeComparator();
		PriorityQueue <Call> queue = new PriorityQueue <Call>(comparator);

		int time = 0;
		int profit = 0;
		Call currentCall = null;
		int currentCallTaken = 0;

		for ( int i = 0; i < 10; i++ ) {
			queue.add( Call.randomCall( time ) );
		}
		currentCall = queue.remove();

		for ( time = 0; time < 8*60; time++ ) {
			if ( time-currentCallTaken == currentCall.getDuration() ) {
				profit += currentCall.getProfit();

				do {
					currentCall = queue.remove();
				} while ( !queue.isEmpty() && time-currentCall.getTimestamp() > 60 );
				currentCallTaken = time;

				queue.add( Call.randomCall( time ) );
				System.out.println( "Time: " + time + "min, Profit: $" + profit + ", Queue: " + queue.size() );
			}
		}

	}

	public static void maxProfit() {// gleicher ablauf nur mit ProfitComparator
		Comparator<Call> comparator = new ProfitComparator();
		PriorityQueue <Call> queue = new PriorityQueue <Call>(comparator);
		int time = 0;
		int profit = 0;
		Call currentCall = null;
		int currentCallTaken = 0;
		for ( int i = 0; i < 10; i++ ) {
			queue.add( Call.randomCall( time ) );
		}
		currentCall = queue.remove();
		for ( time = 0; time < 8*60; time++ ) {
			if ( time-currentCallTaken == currentCall.getDuration() ) {
				profit += currentCall.getProfit();
				do {
					currentCall = queue.remove();
				} while ( !queue.isEmpty() && time-currentCall.getTimestamp() > 60 );
				currentCallTaken = time;
				queue.add( Call.randomCall( time ) );
				System.out.println( "Time: " + time + "min, Profit: $" + profit + ", Queue: " + queue.size() );
			}
		}
	}

}

class TimeComparator implements Comparator <Call> { // Comparator für die Prioritiyqueue
	@Override
	public int compare(Call c1, Call c2) {
		if (c1.getTimestamp() > c2.getTimestamp()) {
			return -1;
		}
		else if (c1.getTimestamp() < c2.getTimestamp()) {
			return 1;
		}
		else return 0;
	}

}

class ProfitComparator implements Comparator <Call> { // Comparator für die Prioritiyqueue
	@Override
	public int compare(Call c1, Call c2) {
		if (c1.getProfit() > c2.getProfit()) {
			return -1;
		}
		else if (c1.getProfit() < c2.getProfit()) {
			return 1;
		}
		else return 0;
	}
}
