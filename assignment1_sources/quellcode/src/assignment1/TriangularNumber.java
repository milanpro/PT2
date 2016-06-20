package assignment1;

/***
 * @author Annika Baldi, Milan Proell
 */
public class TriangularNumber {
    public static void main(String[] args) {
        //Sorgt daf�r, dass der Code auf Code Ocean ausgef�hrt wird.
        System.out.println(hex(7));
    }

    public static String hex(int n) {
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
}