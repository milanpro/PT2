package assignment2;

import java.util.HashSet;
import java.util.Iterator;

public class WordSearch {

    public static void main(String[] args) {

        HashSet<String> words = new HashSet<String>();
        words.add("FoO");
        words.add("bar");
        words.add("BAZ");

        char[][] grid = new char[][]{
                {'B', 'A', 'Z', 'R'},
                {'X', 'X', 'F', 'A'},
                {'X', 'X', 'O', 'B'},
                {'X', 'X', 'O', 'X'}
        };

        grid = search(words, grid);
        print(grid);
    }


    public static char[][] search(HashSet<String> words, char[][] grid) {
        char[][] newGrid = new char[grid.length][grid.length];
        for (int y = 0; y < grid.length; y++) {
            for (int z = 0; z < grid.length; z++) {
                newGrid[y][z] = (char) 45; // auffüllen des neuen grids mit -
            }
        }
        Iterator it = words.iterator();
        while (it.hasNext()) {
            String word = (String) it.next(); //jedes Wort aus words wird abgerufen
            String uword = word.toUpperCase(); // da es keine kleinbuchstaben gibt.
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid.length; j++) {
                    if (grid[i][j] == uword.charAt(0)) {//durchs grid laufen und jedes zeichen mit dem ersten buchstaben des wortes vergleichen
                        int vertical = vertcomp(grid, i, j, uword);
                        int horizontal = horicomp(grid, j, i, uword);
                        if (uword.length() == 1) newGrid[i][j] = uword.charAt(0); //falls wort 1 lang ist?
                        else {
                            if (vertical == j && horizontal == i) {
                                //dann tu nix
                            } else if (horizontal == i) {
                                if (vertical > j) {
                                    for (int k = j; k <= vertical; k++) {
                                        //vertical nennt den endindex, bei dem das wort auf vertikaler ebene endet
                                        newGrid[i][k] = uword.charAt(k - j);
                                    }
                                } else {
                                    for (int k = j; k >= vertical; k--) {
                                        //vertical nennt den endindex, bei dem das wort auf vertikaler ebene endet
                                        newGrid[i][k] = uword.charAt(j - k);
                                    }
                                }
                            } else {
                                if (horizontal > i) {
                                    for (int k = i; k <= horizontal; k++) {
                                        //vertical nennt den endindex, bei dem das wort auf vertikaler ebene endet
                                        newGrid[k][j] = uword.charAt(k - i);
                                    }
                                } else {
                                    for (int k = i; k >= horizontal; k--) {
                                        //vertical nennt den endindex, bei dem das wort auf vertikaler ebene endet
                                        newGrid[k][j] = uword.charAt(i - k);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return newGrid;
    }

    public static int vertcomp(char[][] grid, int h, int v, String word) {
        int compared = v;
        for (int i = (v + 1); i < grid.length; i++) {
            //warum geht er hier nicht bis zum rand des grids??
            if (compared - v == word.length() - 1) {
                return compared; //wort komplett gefunden
            }
            if (i == grid.length) {

            } else {
                if ((i - v) < word.length() && grid[h][i] == word.charAt(i - v)) {
                    compared = compared + 1; // vertikal bis zum grid.length oder wortende schauen, ob wort enthalten.
                }
            }

        }
        compared = v;
        for (int j = (v - 1); j >= -1; j--) {
            //warum geht er hier nicht bis zum rand des grids??
            if ((v - compared) == (word.length() - 1)) {
                return compared;//wort vertikal nicht gefunden oder komplett gefunden
            }
            if (j == -1) {

            } else {
                if ((v - j) < word.length() && grid[h][j] == word.charAt(v - j))
                    compared = compared - 1; //andere Richtung suchen
            }
        }
        return v;
    }


    public static int horicomp(char[][] grid, int h, int v, String word) { //kopie von vertcomp --> später versuch zu vereinfachen
        int compared = v;
        for (int i = (v + 1); i <= grid.length; i++) {

            if ((compared - v) == (word.length() - 1)) {
                return compared; //wort komplett gefunden
            }
            if (i == grid.length) {
                //warum funktioniert es erst mit der if abfrage und nicht anders??
            } else {
                if ((i - v) < word.length() && grid[i][h] == word.charAt(i - v))
                    compared = compared + 1; // vertikal bis zum grid.length oder wortende schauen, ob wort enthalten.
            }

        }
        compared = v;
        for (int j = (v - 1); j >= -1; j--) {

            if ((v - compared) == (word.length() - 1)) {
                return compared;//wort vertikal nicht gefunden oder komplett gefunden
            }
            if (j == -1) {
                //komplette verwirrung!!
            } else {
                if ((v - j) < word.length() && grid[j][h] == word.charAt(v - j))
                    compared = compared - 1; //andere Richtung suchen
            }
        }
        return v;
    }


    /***
     * Fills  given grid with random chars
     *
     * @param grid with the words filled in
     * @return the filled grid
     */
    public static char[][] randomFill(char[][] grid) {
        char[] chars = {66, 67, 68, 70, 71, 72, 74, 75, 76, 77, 78, 80, 81, 82, 83, 84, 86, 87, 88, 89, 90};
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == '\u0000') { // \u0000 is the default initialized value of a char
                    grid[i][j] = chars[(int) (Math.random() * chars.length)];
                }
            }
        }
        return grid;
    }

    /***
     * Outputs a grid to the console
     *
     * @param grid
     */
    public static void print(char[][] grid) {
        String output = "";
        for (char[] row : grid) {
            for (char c : row) {
                output += c;
            }
            output += "\n";
        }
        System.out.print(output);
    }


}
