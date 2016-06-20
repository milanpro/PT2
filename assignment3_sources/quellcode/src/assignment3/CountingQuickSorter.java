package assignment3;

public class CountingQuickSorter extends CountingSorter {

    @Override
    protected String name() {
        return "Quicksort";
    }

    /**
     * Partition the array of integers `a` according to the current pivot at the lower boundary `l`
     *
     * @param a The array of integers
     * @param l The lower boundary
     * @param u The upper boundary
     * @param p The pivotal index
     * @return The next position of the pivot
     **/
    private int partition(int[] a, int l, int u, int p) {
        // Save both the key and the value of the current pivot
        int pk = l;
        int pv = a[p];
        // Relocate the pivot to the end of `a`
        swap(a, p, u);
        // Swap each inadequate element
        for (int i = l; i < u; i++) {
            if (isLessEq(a[i], pv)) {
                swap(a, i, pk++);
            }
        }
        // Relocate the pivot to its original position
        swap(a, u, pk);
        return pk;
    }

    /**
     * Sort the partition ranging from `l` to `u` within the array of integers `a` recursively
     *
     * @param a The array of integers
     * @param l The lower boundary
     * @param u The upper boundary
     **/
    private void quicksortHelper(int[] a, int l, int u) {
        // Use leftmost element as the pivot.
        int p = l;
        if (u > l) {
            // Create two partitions
            int pk = partition(a, l, u, p);
            // Sort both partitions
            quicksortHelper(a, l, pk - 1);
            quicksortHelper(a, pk + 1, u);
        }
    }

    /***
     * Sorts an array using Qicksort
     *
     * @param a The array to be sorted
     */
    @Override
    protected void sortImpl(int[] a) {
        quicksortHelper(a, 0, a.length - 1);
    }
}
