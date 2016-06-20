package assignment1;

/***
 * @author Annika Baldi, Milan Proell
 */
public class Fibonacci {
    public static void main(String[] args) {
        //Sorgt dafür, dass der Code auf Code Ocean ausgeführt wird.
        //System.out.println(iterative(6));
        System.out.println(recursive(47));
    }

    public static long iterative(long n) {
        if (n <= 0 || n > 50) {
            throw new IllegalArgumentException("out of definition");
        } else if (n <= 2) {
            return 1L;
        } else {
            long fib = 1;
            long fib1 = 1;
            long temp = 0;
            for (int i = 3; i <= n; i++) {
                temp = fib + fib1;
                fib1 = fib;
                fib = temp;
            }
            return fib;
        }
    }

    public static String hexRecur(int n) {
        if (n >= 1) {
            long tri = 0;
            for (int i = 0; i <= n; i++) {
                tri = tri + i;
            }
            return Long.toHexString(tri);
        } else {
            throw new IllegalArgumentException("Zahl au�erhalb des Definitionsbereiches");
        }
    }

    public static long recursive(long n) {
        if (n <= 0 || n > 50) {
            throw new IllegalArgumentException("out of definition");
        } else if (n <= 2) {
            return 1L;
        } else {
            return recursive(n - 1) + recursive(n - 2);
        }
    }
}