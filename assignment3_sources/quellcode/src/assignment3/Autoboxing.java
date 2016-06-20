package assignment3;

import java.util.ArrayList;


public class Autoboxing {

    /***
     * ERGEBNISSE:
     * <p>
     * 1.
     * Zeit für int array mit int Werten 0,5 s
     * Zeit für int array mit Integer Werten zwischen 1.2 und 2.8 s
     * Zeit für Integer array mit int Werten zwischen 6 und 16 s
     * Zeit für Integer array mit Integer Werten zwischen 13 und 23 s
     * <p>
     * 2.
     * arraysize: 200000
     * Speicher für int array: 800016 (4 Bytes/int)
     * Speicher für Integer array: 3484400 (17 Bytes/int)
     * Speicher für ArrayList an Integer: 4826576 (24 Bytes/int)
     * <p>
     * 3. Bei zu kleinen arrays nimmt Java vermutlich vorreservierten Speicher
     * und deswegen kommt dann ein verbrauch von 0 Byte raus.
     * Bei zu großen arrays schätzt Java vermutlich den Speicher und reserviert dann einen Block der groß genug ist,
     * da das einzelne reservieren zu ressouercenaufwändig wäre.
     */

    public static void main(String[] args) {
        /*
        long before = System.nanoTime();

        compareIntIntegerRuntime();


        long after = System.nanoTime();
        double duration = (after-before)/1e9;
        System.out.println("Zeit: "+duration+" s");
        */
        compareIntIntegerSize(200000);

    }

    public static void compareIntIntegerRuntime() {
/*		Integer[] array1 = new Integer[10000000];
        for(int i = 0; i<10000000 ; i++){
		    array1[i] = (i * 8)% 10000;
		}

		int r = 0;    //int in int Array
*		do {
*			int[] tollesarray = new int[10000000];
*			for (int i = 0; i < 10000000; i++) {
*				tollesarray[i] = (i * 8) % 10000;
*			}
*			r++;
*		}while(r < 24);
*
*		int r = 0;
        do {
            int[] tollesarray = new int[10000000];
            for (Integer i = 0; i < 10000000; i++) {
                tollesarray[i] = (i * 8) % 10000;
            }
            r++;
        }while(r < 24);
*		int r = 0;
        do {
            Integer[] tollesarray = new Integer[10000000];
            for (int i = 0; i < 10000000; i++) {
                tollesarray[i] = (i * 8) % 10000;
            }
            r++;
        }while(r < 24);
*/
        int r = 0;
        do {
            Integer[] tollesarray = new Integer[10000000];
            for (Integer i = 0; i < 10000000; i++) {
                tollesarray[i] = (i * 8) % 10000;
            }
            r++;
        } while (r < 24);
    }


    public static void compareIntIntegerSize(int arraySize) {
        long startMem;
        long endMem;

        // int array
        startMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        int[] intArr = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            intArr[i] = (int) ((Math.random() - 1) * Integer.MAX_VALUE);
        }
        endMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Speicher für int array: " + (endMem - startMem) + " (" + ((endMem - startMem) / arraySize) + " Bytes/Wert)");

        // Integer array
        startMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        Integer[] IntegerArr = new Integer[arraySize];
        for (int i = 0; i < arraySize; i++) {
            IntegerArr[i] = (int) ((Math.random() - 1) * Integer.MAX_VALUE);
        }
        endMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Speicher für Integer array: " + (endMem - startMem) + " (" + ((endMem - startMem) / arraySize) + " Bytes/Wert)");

        // ArrayList an Integer
        startMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        ArrayList<Integer> IntegerArrList = new ArrayList<Integer>(arraySize);
        for (int i = 0; i < arraySize; i++) {
            IntegerArrList.add((int) ((Math.random() - 1) * Integer.MAX_VALUE));
        }
        endMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Speicher für ArrayList an Integer: " + (endMem - startMem) + " (" + ((endMem - startMem) / arraySize) + " Bytes/Wert)");

    }
}