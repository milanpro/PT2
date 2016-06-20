package assignment4;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MergesortTest {

    @Test
    public void testSort() {
        ArrayList<Integer> data = new ArrayList<>();
        data.add(1);
        data.add(3);
        data.add(2);
        data.add(10);
        data.add(3);
        data.add(22);
        data.add(2);
        DivideAndConquer.mergesort(data);
        assertEquals(Arrays.asList(1, 2, 2, 3, 3, 10, 22), data);
    }

    @Test
    public void testSortEmpty() {
        ArrayList<Integer> data = new ArrayList<>();
        DivideAndConquer.mergesort(data);
        assertEquals(new ArrayList<>(), data);
    }

}