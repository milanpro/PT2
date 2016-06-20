package assignment2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Sort {

    public static void main(String[] args) throws IOException {

        System.out.println("Main-Methode");
        FileReader fr = new FileReader("bin/assignment2/resources/large_array.txt");
        BufferedReader br = new BufferedReader(fr);
        String[] test = br.readLine().split(",");
        int[] largearray = new int[test.length];
        int i = 0;
        for (String s : test) {
            largearray[i] = Integer.parseInt(s);
            i++;
        }
        quicksort(largearray);
    }

    public static void bubblesort(int[] a) {
        for (int j = a.length; j > 1; j = j - 1) {
            for (int i = 0; i < j - 1; i = i + 1) {
                if (a[i] > a[i + 1]) swap(a, i, i + 1);
            }
        }

    }

    public static void swap(int[] a, int ind, int ind1) {
        int temp = a[ind];
        a[ind] = a[ind1];
        a[ind1] = temp;
    }

    public static void quicksort(int[] a) {
        qsort(0, a.length - 1, a);
    }

    public static void qsort(int l, int r, int[] a) {
        if (l < r) {
            int teiler = teile(l, r, a);
            qsort(l, teiler - 1, a);
            qsort(teiler + 1, r, a);
        }
    }

    public static int teile(int l, int r, int[] a) {
        int i = l;
        int j = r - 1;
        int pivot = a[r];

        do {
            while (a[i] <= pivot && i < r) i = i + 1;
            while (a[j] >= pivot && j > l) j = j - 1;
            if (i < j) swap(a, i, j);
        } while (i < j);

        if (a[i] > pivot) swap(a, i, r);
        return i;
    }

    public static void countingsort(int[] a) {
        int max = 0;
        for (int s : a) {
            if (max < s) max = s;
        }
        int[] arr = new int[max + 1];
        for (int i = 0; i < a.length; i = i + 1) arr[a[i]] = arr[a[i]] + 1;
        int elem = 0;
        for (int k = 0; k <= max; k = k + 1) {
            while (arr[k] > 0) {
                a[elem] = k;
                elem = elem + 1;
                arr[k] = arr[k] - 1;
            }
        }
    }
}
