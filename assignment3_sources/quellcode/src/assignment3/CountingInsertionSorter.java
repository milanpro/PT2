package assignment3;

public class CountingInsertionSorter extends CountingSorter {

    @Override
    protected String name() {
        return "Insertionsort";
    }

    /***
     * Sorts an array using Insertionsort
     *
     * @param a The array to be sorted
     */
    @Override
    protected void sortImpl(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int j = i;
            while ((j > 0) && isLess(a[j], a[j - 1])) {
                swap(a, j, j - 1);
                j--;
            }
        }
    }
}
