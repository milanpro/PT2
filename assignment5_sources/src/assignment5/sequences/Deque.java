package assignment5.sequences;

import java.util.NoSuchElementException;

/**
 * A special queue which allows accessing elements at both ends of the queue.
 *
 * @param <T> The type of elements stored in this sequence.
 */
public interface Deque<T> extends FixedSequence<T>, Queue<T> {
    /**
     * Adds an element to the front of the queue.
     * @param element The element to be added.
     */
    void push_front(T element);

    /**
     * Removes the element at the back of the queue.
     *
     * @return The element currently at the back of the queue.
     * @throws NoSuchElementException if the queue is currently empty.
     */
    T pop_back();
}
