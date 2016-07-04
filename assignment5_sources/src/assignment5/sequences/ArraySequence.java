package assignment5.sequences;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * A sequence which manages it's elements as an array.
 *
 * @param <T> The type of elements stored in this sequence.
 */
public class ArraySequence<T> implements Sequence<T>, List<T>, Stack<T>, Queue<T> {
    private T[] values;

    public ArraySequence() {
        this.values = (T[]) new Object[0];
    }

    @Override
    public void insert(int index, T element) {
        if(element == null) return;

        if(index>values.length||index<0){
            throw new IndexOutOfBoundsException();
        }
        T[] tempvalues = values;
        this.values = (T[]) new Object[tempvalues.length+1];
        for (int i = 0;i<index;i++){
            values[i] = tempvalues[i];
        }
        values[index] = element;
        for (int i = index+1;i<values.length;i++){
            values[i] = tempvalues[i-1];
        }
    }

    @Override
    public T set(int index, T element) {
        if(index>=values.length||index<0) throw new NoSuchElementException();
        T temp = values[index];
        values[index] = element;
        return temp;
    }

    @Override
    public T get(int index) {
        if(index>=values.length||index<0) throw new NoSuchElementException();
        return values[index];
    }

    @Override
    public T remove(int index) {
        if(index>=values.length||index<0) throw new NoSuchElementException();
        T[] tempvalues = values;
        this.values = (T[]) new Object[tempvalues.length-1];
        for(int i = 0;i<index;i++) values[i]=tempvalues[i];
        for(int i = index+1;i<tempvalues.length;i++)values[i-1] = tempvalues[i];
        return tempvalues[index];
    }

    @Override
    public void push_back(T element) {
        T[] tempvalues = values;
        this.values = (T[]) new Object[tempvalues.length+1];
        for (int i = 0;i<tempvalues.length;i++){
            this.values[i] = tempvalues[i];
        }
        this.values[tempvalues.length] = element;
    }

    @Override
    public T pop_front() {
        if(values.length==0) throw new NoSuchElementException();
        return remove(0);
    }

    @Override
    public void push_top(T element) {
        insert(0,element);
    }

    @Override
    public T pop_top() {
        if(values.length==0) throw new NoSuchElementException();
        return remove(0);
    }

    @Override
    public int size() {
        return values.length;
    }
}
