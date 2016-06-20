package assignment2;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class SortTest {
    private TestUtils util = new TestUtils();

    @Test
    public void testBubblesort() {
        int[] actual = {90, 2, 43, 23, 12, 18, 9, 900, 41, 41, 41, 42, 41};
        Sort.bubblesort(actual);
        int[] expected = {2, 9, 12, 18, 23, 41, 41, 41, 41, 42, 43, 90, 900};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testBubblesortBig() {
        int[] actual = util.readIntArrayResource("/assignment2/resources/big_array.txt");
        Sort.bubblesort(actual);
        int[] expected = util.readIntArrayResource("/assignment2/resources/big_array.sorted.txt");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testQuicksort() {
        int[] actual = {90, 2, 43, 23, 12, 18, 9, 900, 41, 41, 41, 42, 41};
        Sort.quicksort(actual);
        int[] expected = {2, 9, 12, 18, 23, 41, 41, 41, 41, 42, 43, 90, 900};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testQuicksortBig() {
        int[] actual = util.readIntArrayResource("/assignment2/resources/big_array.txt");
        Sort.quicksort(actual);
        int[] expected = util.readIntArrayResource("/assignment2/resources/big_array.sorted.txt");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testCountingsort() {
        int[] actual = {90, 2, 43, 23, 12, 18, 9, 900, 41, 41, 41, 42, 41};
        Sort.countingsort(actual);
        int[] expected = {2, 9, 12, 18, 23, 41, 41, 41, 41, 42, 43, 90, 900};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testCountingsortBig() {
        int[] actual = util.readIntArrayResource("/assignment2/resources/big_array.txt");
        Sort.countingsort(actual);
        int[] expected = util.readIntArrayResource("/assignment2/resources/big_array.sorted.txt");
        assertArrayEquals(expected, actual);
    }

}
