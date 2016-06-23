package assignment5.sequences;

import java.util.NoSuchElementException;

/**
 * A sequence which manages it's elements as a circular buffer while limiting the maximum number of elements in the
 * list.
 *
 * @param <T> The type of elements stored in this sequence.
 * @see <a href="https://en.wikipedia.org/wiki/Circular_buffer">https://en.wikipedia.org/wiki/Circular_buffer</a>
 */
public class FixedArraySequence<T> extends ArraySequence<T> implements FixedSequence<T>, Deque<T> {
    /**
     * @param maxSize The maximum number of elements this sequence can hold.
     * @throws IllegalArgumentException if maxSize is negative.
     */
    public FixedArraySequence(int maxSize) {

    }

    @Override
    public void push_front(T element) {

    }

    @Override
    public T pop_back() {
        return null;
    }

    @Override
    public int maximumSize() {
        return 0;
    }
}
