package assignment4;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TripToRenoTest {

    @Test
    public void testBattle1() {
        int[] tankstellen = {1, 1, 1, 20, 21, 34, 1, 2, 45, 1, 44, 7, 5, 10};
        int ret = TripToReno.timeCompare(50, 40, 45, 200, tankstellen);
        assertEquals(0, ret);
    }

    @Test
    public void testStops1() {
        int[] tankstellen = {34, 37, 19};
        int[] ret = TripToReno.getStops(50, 100, tankstellen);
        assertArrayEquals(new int[]{0, 1}, ret);
    }

}