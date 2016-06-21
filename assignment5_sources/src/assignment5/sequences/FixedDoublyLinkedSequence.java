package assignment5.sequences;

/**
 * A sequence which manages it's elements as a doubly linked list while limiting the maximum number of elements in the
 * list.
 *
 * @param <T> The type of elements stored in this sequence.
 */
public class FixedDoublyLinkedSequence<T> extends DoublyLinkedSequence<T> implements FixedSequence<T>, Deque<T> {
    /**
     * @param maxSize The maximum number of elements this sequence can hold.
     * @throws IllegalArgumentException if maxSize is negative.
     */
    public FixedDoublyLinkedSequence(int maxSize) {

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
