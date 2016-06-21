package assignment5.sequences;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public abstract class SequenceTest<T extends Sequence<Integer> & List<Integer> & Stack<Integer> & Queue<Integer>> {
    protected abstract T newInstance();

    @Test
    public void basicList() {
        List<Integer> list = newInstance();

        assertEquals(0, list.size());
        list.insert(0, 42);
        assertEquals(1, list.size());
        assertEquals(42, (int) list.get(0));
        list.insert(0, 24);
        assertEquals(2, list.size());
        assertEquals(42, (int) (list.get(1)));
        assertEquals(24, (int) (list.get(0)));
        list.insert(2, 66);
        list.insert(2, 77);
        assertEquals(77, (int) list.set(2, 88));
        assertEquals(88, (int) list.remove(2));
        assertEquals(3, list.size());
        assertEquals(42, (int) list.remove(1));
        assertEquals(66, (int) list.set(1, -666));
        assertEquals(24, (int) list.remove(0));
        assertEquals(-666, (int) list.remove(0));
        assertEquals(0, list.size());

        list.insert(0, -3015);
        list.insert(1, -3016);
        list.insert(2, -3017);
        list.insert(3, -3018);
        assertEquals(-3017, (int) list.remove(2));
        assertEquals(-3018, (int) list.remove(2));
        assertEquals(-3015, (int) list.remove(0));
        assertEquals(-3016, (int) list.remove(0));
        assertEquals(0, list.size());
    }

    @Test
    public void basicStack() {
        Stack<Integer> stack = newInstance();

        assertEquals(0, stack.size());
        stack.push_top(71);
        assertEquals(1, stack.size());
        stack.push_top(81);
        assertEquals(2, stack.size());
        assertEquals(81, (int) stack.pop_top());
        stack.push_top(-1);
        stack.push_top(-1);
        stack.push_top(-2);
        assertEquals(4, stack.size());
        assertEquals(-2, (int) stack.pop_top());
        assertEquals(-1, (int) stack.pop_top());
        assertEquals(-1, (int) stack.pop_top());
        assertEquals(71, (int) stack.pop_top());
        assertEquals(0, stack.size());
    }

    @Test
    public void basicQueue() {
        Queue<Integer> queue = newInstance();

        assertEquals(0, queue.size());
        queue.push_back(7);
        assertEquals(1, queue.size());
        queue.push_back(13);
        assertEquals(2, queue.size());
        assertEquals(7, (int) queue.pop_front());
        queue.push_back(17);
        queue.push_back(19);
        queue.push_back(23);
        assertEquals(4, queue.size());
        assertEquals(13, (int) queue.pop_front());
        assertEquals(17, (int) queue.pop_front());
        assertEquals(19, (int) queue.pop_front());
        assertEquals(23, (int) queue.pop_front());
        assertEquals(0, queue.size());
    }
}
