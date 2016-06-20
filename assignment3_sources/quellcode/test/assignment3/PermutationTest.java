package assignment3;

import org.junit.Test;

import java.util.Arrays;
import java.util.Vector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PermutationTest {

    public boolean contains(Vector<int[]> v, int[] a) {
        if (v.size() == 0) {
            return false;
        }
        for (int[] b : v) {
            if (Arrays.equals(a, b)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void testNumberOfPermutations() {
        int[] a = {1, 2, 3, 4};
        assertEquals(Permutation.permutations(a).size(), 24);
    }

    @Test
    public void testContainsReverse() {
        int[] a = {1, 2, 3, 4};
        int[] reverse = {4, 3, 2, 1};
        assertTrue(contains(Permutation.permutations(a), reverse));
    }

    @Test
    public void testContainsSelf() {
        int[] a = {1, 2, 3, 4};
        assertTrue(contains(Permutation.permutations(a), a));
    }

    @Test
    public void testContainsSomePermutation() {
        int[] a = {1, 2, 3, 4};
        int[] somePermutation = {2, 1, 4, 3};
        assertTrue(contains(Permutation.permutations(a), somePermutation));
    }
}
