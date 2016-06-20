package assignment3;

public class CountingBubbleSorter extends CountingSorter {

    @Override
    protected String name() {
        return "Bubblesort";
    }

    /***
     * Sorts an array using bubblesort.
     *
     * @param a The array to be sorted
     */
    @Override
    protected void sortImpl(int[] a) {
        for (int j = 0; j < a.length; j++) {
            for (int i = 0; i < a.length - 1; i++) {
                if (isLess(a[i + 1], a[i])) {
                    swap(a, i, i + 1);
                }
            }
        }
    }
}
