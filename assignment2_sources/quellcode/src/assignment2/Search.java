package assignment2;

import java.util.Arrays;

public class Search {

    public static void main(String[] args) {
        System.out.println("Main-Methode");
        int[] small = new int[]{2, 4, 4, 5, 6, 6};

        System.out.println(Arrays.toString(findSorted(4, small)));
        System.out.println(Arrays.toString(findSorted(6, small)));
    }


    public static int[] findUnsorted(int x, int[] a) {
        int index = 0;
        int index1 = 0;
        int[] result = new int[a.length];
        for (Integer s : a) {
            if (s == x) {
                result[index1] = index;
                index1 = index1 + 1;
            }
            index = index + 1;
        }
        int[] result1 = new int[index1];
        for (int i = 0; i < index1; i++) {
            result1[i] = result[i];
        }
        return result1;
    }

    public static int[] findSorted(int x, int[] a) {
        int index = 0;
        int index1 = 0;
        int[] result = new int[a.length];
        for (Integer s : a) {
            if (s == x) {
                result[index1] = index;
                index1 = index1 + 1;
            } else if (s != x && index1 == 0) {

            } else {
                break;
            }
            index = index + 1;
        }
        int[] result1 = new int[index1];
        for (int i = 0; i < index1; i++) {
            result1[i] = result[i];
        }
        return result1;
    }
}

