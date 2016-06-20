package assignment3;

import java.util.Vector;

public class CountingSortComparison {

    public static void main(String[] args) {
        System.out.println("Execution starts here!");
        compareAlgorithms();

    }

    /***
     * Creates all permutations of the numbers 1 to 10 and sorts them using quicksort, countingsort and insertionsort. Prints the gathered statistics about each sorting method
     */
    public static void compareAlgorithms() {
        //TODO: Compare algorithms for all permutations of i between 1 and 10
        int[] z = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Vector<int[]> v1 = Permutation.permutations(z);
        Vector<int[]> v2 = Permutation.permutations(z);
        Vector<int[]> v3 = Permutation.permutations(z);

        CountingQuickSorter quicksort = new CountingQuickSorter();
        for (int[] element : v1) {
            quicksort.sort(element);
        }

        CountingBubbleSorter bubblesort = new CountingBubbleSorter();
        for (int[] element : v2) {
            bubblesort.sort(element);
        }

        CountingInsertionSorter insertionsort = new CountingInsertionSorter();
        for (int[] element : v3) {
            insertionsort.sort(element);
        }
        quicksort.printResults();
        bubblesort.printResults();
        insertionsort.printResults();

        /***
         * RESULTS:
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         */

    }
}
