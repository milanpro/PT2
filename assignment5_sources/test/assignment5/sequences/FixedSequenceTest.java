package assignment5.sequences;

import org.junit.Test;

import static org.junit.Assert.*;

public abstract class FixedSequenceTest <T extends Sequence<Integer> & FixedSequence<Integer> & List<Integer> & Stack<Integer> & Queue<Integer> & Deque<Integer>> extends SequenceTest<T> {
    protected T newInstance() {
        return newInstance(4);
    }

    protected abstract T newInstance(int maxSize);

    @Test
    public void basicDeque() {
        Deque<Integer> deque = newInstance();

        assertEquals(0, deque.size());
        assertEquals(4, deque.maximumSize());

        deque.push_front(2016);
        deque.push_front(2017);
        assertEquals(2016, (int)deque.pop_back());
        assertEquals(2017, (int)deque.pop_back());

        assertEquals(0, deque.size());
        assertEquals(4, deque.maximumSize());

        deque.push_front(-20);
        assertEquals(1, deque.size());
        assertEquals(4, deque.maximumSize());
        deque.push_back(31);
        deque.push_front(-42);
        deque.push_back(53);
        assertEquals(4, deque.size());
        assertEquals(4, deque.maximumSize());
        assertEquals(53, (int)deque.pop_back());
        assertEquals(31, (int)deque.pop_back());
        assertEquals(-20, (int)deque.pop_back());
        assertEquals(-42, (int)deque.pop_front());
        assertEquals(0, deque.size());
        assertEquals(4, deque.maximumSize());

        deque.push_back(2016);
        deque.push_back(2017);
        assertEquals(2017, (int)deque.pop_back());
        assertEquals(2016, (int)deque.pop_back());
        deque.push_front(2018);
        deque.push_front(2019);
        deque.push_front(2020);
        assertEquals(2018, (int)deque.pop_back());
        assertEquals(2019, (int)deque.pop_back());
        assertEquals(2020, (int)deque.pop_back());

        assertEquals(0, deque.size());
        assertEquals(4, deque.maximumSize());
    }
}
