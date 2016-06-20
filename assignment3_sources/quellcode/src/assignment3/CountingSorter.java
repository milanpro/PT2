package assignment3;

public abstract class CountingSorter {

    //The number of swaps and Comparisons in the current run.
    protected int swapCount, compCount;

    //Statistics for all arrays sorted by this object.
    protected int maxSwap = 0, minSwap, sumSwap, maxComp = 0, minComp, sumComp;

    //The total number of array sorted (i.e. how often sort() was called)
    protected int runCount;

    //Copies of the worst and best permutations.
    protected int[] bestCompPermutation, worstCompPermutation, bestSwapPermutation, worstSwapPermutation;

    public CountingSorter() {
        minSwap = Integer.MAX_VALUE;
        minComp = Integer.MAX_VALUE;
    }

    /***
     * Workaround to avoid using Arrays.toString which does not work on codeOcean. Converts an int Array to a String.
     *
     * @param a an array to be converted to String.
     * @return a String representation of a.
     */
    public static String arrayToString(int[] a) {
        if (a == null) {
            return "null";
        }

        String result = "[";
        if (a.length > 0) {
            result += a[0];
        }
        for (int i = 1; i < a.length; i++) {
            result += ", " + a[i];
        }
        result += "]";
        return result;
    }

    abstract protected String name();

    /***
     * Resets the current counters for swap and comparison counts.
     */
    protected void reset() {
        swapCount = 0;
        compCount = 0;
    }

    /***
     * Given an array, swaps the two elements indicated by their indices
     *
     * @param a array
     * @param i first index
     * @param j second index
     */
    protected void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        swapCount++;
    }

    /***
     * Compares two numbers and returns whether the first is less than the second
     *
     * @param a first number
     * @param b second number
     * @return whether a is less than b
     */
    protected boolean isLess(int a, int b) {
        compCount++;
        return a < b;
    }

    /***
     * Compares a comparison between two numbers and returns whether the first is less or equals than the second
     *
     * @param a first number
     * @param b second number
     * @return whether a is less or equal than b
     */
    protected boolean isLessEq(int a, int b) {
        compCount++;
        return a <= b;
    }

    abstract protected void sortImpl(int[] a);

    /***
     * Method that gets called from outside. This triggers a call to sortImpl and monitors the number of comparisons and swaps
     *
     * @param a an array that is to be sorted
     */
    public void sort(int[] a) {
        /***
         *
         *TODO implement. Be sure to call sortImpl() at some point in this function to sort the array.
         *All members of this Class should be updated appropriately in this function.
         */
        reset();
        runCount++;
        int[] b = new int[a.length];
        System.arraycopy(a, 0, b, 0, a.length);
        sortImpl(a);
        sumComp = sumComp + compCount;
        sumSwap = sumSwap + swapCount;
        if (swapCount < minSwap) {
            bestSwapPermutation = b;
            minSwap = swapCount;
        }
        if (compCount < minComp) {
            bestCompPermutation = b;
            minComp = compCount;
        }
        if (swapCount > maxSwap) {
            worstSwapPermutation = b;
            maxSwap = swapCount;
        }
        if (compCount > maxComp) {
            worstCompPermutation = b;
            maxComp = compCount;
        }


        //protected int[] bestCompPermutation, worstCompPermutation, bestSwapPermutation, worstSwapPermutation;

        /***
         * Keep in mind: sorting changes the original array. As such, you will want to create a new array to do the sorting on,
         * otherwise you may run into problems on the way if arrays are getting re-used!
         * int[] oldA = new int[a.length];
         * System.arraycopy(a, 0, oldA, 0, a.length);
         */

    }

    /***
     * Prints the collected statistics in a readable format.
     */
    public void printResults() {
        System.out.println(name());
        System.out.printf("  Arrays sorted: %d%n", runCount);
        System.out.printf("  Comparisons:%n    max: %d, min: %d sum: %d%n", maxComp, minComp, sumComp);
        System.out.println("    Worst Permutation: " + arrayToString(worstCompPermutation));
        System.out.println("    Best Permutation: " + arrayToString(bestCompPermutation));
        System.out.printf("  Swaps:%n    max: %d, min: %d sum: %d%n", maxSwap, minSwap, sumSwap);
        System.out.println("    Worst Permutation: " + arrayToString(worstSwapPermutation));
        System.out.println("    Best Permutation: " + arrayToString(bestSwapPermutation));
        System.out.println("");
    }
}
