package assignment3;

import java.util.Vector;


public class Permutation {

    public static void main(String[] args) {
        System.out.println("Permutation");
        int[] arrayz = {3, 4, 6, 2, 1};
        Vector<int[]> v = permutations(arrayz);
        for (int[] element : v) {
            System.out.println(java.util.Arrays.toString(element));
        }

    }

    public static Vector<int[]> permutations(int[] a) {
        Vector<int[]> result = new Vector<int[]>();
        permute(a, 0, result);
        return result;
    }

    static void permute(int[] a, int k, Vector<int[]> perm) { // ausgelagerte permutation für rekursion
        for (int i = k; i < a.length; i++) { // erstellen der Permutationen
            swap(a, i, k);
            permute(a, k + 1, perm);
            swap(a, k, i);
        }
        if (k == a.length - 1) { //beim ende einer Permutation
            int[] b = a.clone(); //klonen des arrays um neuen pointer zu erzeugen
            perm.add(b); //hinzufügen der Permutation zum Array.
        }
    }

    static void swap(int[] a, int i, int k) { // simple swap funktion für arrays
        int temp = a[i];
        a[i] = a[k];
        a[k] = temp;
    }

    public static void sortAllPermutations(int[] a, CountingSorter sorter) {
        for (int[] permutation : permutations(a)) {
            sorter.sort(permutation);
        }
    }
}
