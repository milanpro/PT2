package assignment5.sequences;

/**
 * A sequence which manages it's elements as a doubly linked list while limiting the maximum number of elements in the
 * list.
 *
 * @param <T> The type of elements stored in this sequence.
 */
public class FixedDoublyLinkedSequence<T> extends DoublyLinkedSequence<T> implements FixedSequence<T>, Deque<T> {
    private int maximumsize;
    /**
     * @param maxSize The maximum number of elements this sequence can hold.
     * @throws IllegalArgumentException if maxSize is negative.
     */
    public FixedDoublyLinkedSequence(int maxSize) {
        if (maxSize < 0) throw new IllegalArgumentException();
        else maximumsize = maxSize;

    }

    @Override
    public void push_front(T element) {
        if(this.size()>=maximumsize) throw new IllegalStateException();
        else super.push_top(element);
    }
    @Override
    public void push_top(T element) {
        if(this.size()>=maximumsize) throw new IllegalStateException();
        else super.push_top(element);
    }

    @Override
    public T pop_back() {
        return super.remove(this.size()-1);
    }

    @Override
    public int maximumSize() {
        return this.maximumsize;
    }
    @Override
    public void insert(int index, T element) {
        if(this.size()>=maximumsize) throw new IllegalStateException();
        else super.insert(index,element);
    }
    @Override
    public void push_back(T element) {
        if(this.size()>=maximumsize) throw new IllegalStateException();
        else super.push_back(element);
    }
}
