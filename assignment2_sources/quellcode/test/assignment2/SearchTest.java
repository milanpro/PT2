package assignment2;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class SearchTest {
    private TestUtils util = new TestUtils();

    @Test
    public void testUnsorted() {
        int[] small = new int[]{5, 2, 4, 1, 4};

        assertArrayEquals(new int[]{2, 4}, Search.findUnsorted(4, small));
        assertArrayEquals(new int[]{3}, Search.findUnsorted(1, small));
    }

    @Test
    public void testUnsortedBig() {
        int[] big = util.readIntArrayResource("/assignment2/resources/big_array.txt");

        assertArrayEquals(new int[]{314323}, Search.findUnsorted(9, big));
        assertArrayEquals(new int[]{4954, 77497, 95467, 900624}, Search.findUnsorted(783835, big));
    }

    @Test
    public void testSorted() {
        int[] small = new int[]{1, 2, 4, 4, 5};

        assertArrayEquals(new int[]{2, 3}, Search.findSorted(4, small));
        assertArrayEquals(new int[]{0}, Search.findSorted(1, small));
    }

    @Test
    public void testSortedBig() {
        int[] big = util.readIntArrayResource("/assignment2/resources/big_array.sorted.txt");

        assertArrayEquals(new int[]{17}, Search.findSorted(9, big));
        assertArrayEquals(new int[]{784222, 784223, 784224, 784225}, Search.findSorted(783835, big));
    }
}
