package assignment5.sequences;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * A sequence which manages it's elements as an array.
 *
 * @param <T> The type of elements stored in this sequence.
 */
public class ArraySequence<T> implements Sequence<T>, List<T>, Stack<T>, Queue<T> {
    public ArraySequence() {

    }

    @Override
    public void insert(int index, T element) {

    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public void push_back(T element) {

    }

    @Override
    public T pop_front() {
        return null;
    }

    @Override
    public void push_top(T element) {

    }

    @Override
    public T pop_top() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
