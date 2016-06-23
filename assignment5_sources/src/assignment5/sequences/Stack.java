package assignment5.sequences;

import java.util.NoSuchElementException;

/**
 * A special sequence which supports Last-In-First-Out (LIFO) behaviour.
 *
 * @param <T> The type of elements stored in this sequence.
 */
public interface Stack<T> extends Sequence<T> {
    /**
     * Adds an element on top of the stack.
     * @param element The element to be added.
     */
    void push_top(T element);

    /**
     * Removes the element at the top of the stack.
     *
     * @return The element currently at the top of the stack.
     * @throws NoSuchElementException if the stack is currently empty.
     */
    T pop_top();
}
