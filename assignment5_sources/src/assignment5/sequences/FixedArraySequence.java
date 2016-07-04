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
    private int maximumsize;
    /**
     * @param maxSize The maximum number of elements this sequence can hold.
     * @throws IllegalArgumentException if maxSize is negative.
     */
    public FixedArraySequence(int maxSize) {
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
        return maximumsize;
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
