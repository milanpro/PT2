package assignment5.sequences;

import java.util.NoSuchElementException;

/**
 * A special sequence which supports First-In-First-Out (FIFO) behaviour.
 *
 * @param <T> The type of elements stored in this sequence.
 */
public interface Queue<T> extends Sequence<T> {
    /**
     * Adds an element to the back of the queue.
     * @param element The element to be added.
     */
    void push_back(T element);

    /**
     * Removes the element at the front of the queue.
     *
     * @return The element currently at the front of the queue.
     * @throws NoSuchElementException if the queue is currently empty.
     */
    T pop_front();
}
