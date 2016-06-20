package assignment4;

import java.util.Random;

/**
 * @author Annika Baldi
 * @author Milan Proell
 */
public class SudokuSolver {
    public static boolean solved = false;

    public static void main(String[] args) {
        SudokuBoard created = createSudokuRiddle(10);
        System.out.println(created.toString());
    }

    /**
     * create a new riddle
     *
     * @param allocations number of allocations in the new riddle
     * @return the riddle
     */
    public static SudokuBoard createSudokuRiddle(int allocations) {
        int[][] create = new int[9][9];
        SudokuBoard created = new SudokuBoard(create);
        int x;
        int startwert = 18;

        if (allocations > startwert) x = startwert;
        else x = allocations;
        Random rand;
        for (int i = 0; i < x; i++) {
            rand = new Random();

            // nextInt is normally exclusive of the top value,
            // so add 1 to make it inclusive

            int randomNum = rand.nextInt(9) + 1;
            int ycoord = rand.nextInt(9);
            int xcoord = rand.nextInt(9);
            if (!created.isSet(ycoord, xcoord)) {

                created.setCell(randomNum, ycoord, xcoord);
                if (!fine(created, xcoord, ycoord)) {
                    created.removeValue(ycoord, xcoord);
                    i--;
                }
            } else i--;
        }
        if (allocations <= startwert) return created;
        else {
            if (!solve(created)) {
                System.out.println("startwert zu hoch");
                return null;
            }
            for (int j = 0; j < (81 - allocations); j++) {
                rand = new Random();
                int ycoord = rand.nextInt(9);
                int xcoord = rand.nextInt(9);
                if (created.isSet(ycoord, xcoord)) created.removeValue(ycoord, xcoord);
                else j--;
            }

            return created;
        }

    }

    /**
     * solves the given riddle
     *
     * @param sudoku the riddle
     * @return true if solved, false if not
     */
    public static boolean solve(SudokuBoard sudoku) {
        int x = 0;
        int y = 0;
        int[] next = nextone(sudoku, x, y);
        int newx = next[0];
        int newy = next[1];
        solved = false;
        return rekur(sudoku, newx, newy);
    }

    public static boolean rekur(SudokuBoard sudoku, int x, int y) {
        if (solved) return true;

        int newx;
        int newy;
        int[] next;

        for (int i = 1; i < 10; i++) {
            sudoku.setCell(i, y, x);
            if (fine(sudoku, x, y)) {
                //System.out.println("i: "+i);
                //System.out.println("x: "+x+" y: "+y);
                next = nextone(sudoku, x, y);
                newx = next[0];
                newy = next[1];
                //System.out.println("newx: "+newx+" newy: "+newy);
                if (newx == -1 && newy == -1) {
                    solved = true;
                    return true;
                } else {
                    if (rekur(sudoku, newx, newy) && solved) return true;
                }

            }

        }
        if (solved) {
            return true;
        } else {
            sudoku.removeValue(y, x);
            return false;
        }
    }

    public static boolean fine(SudokuBoard sudoku, int x, int y) {
        for (int z = 0; z < 9; z++) {
            if (z != y && sudoku.getCell(z, x) == sudoku.getCell(y, x)) return false;
        }
        for (int s = 0; s < 9; s++) {
            if (s != x && sudoku.getCell(y, s) == sudoku.getCell(y, x)) return false;
        }
        int x1;
        if (x < 3) x1 = 0;
        else if (x > 5) x1 = 6;
        else x1 = 3;
        int y1;
        if (y < 3) y1 = 0;
        else if (y > 5) y1 = 6;
        else y1 = 3;

        for (int xk = x1; xk < x1 + 3; xk++) {
            for (int yk = y1; yk < y1 + 3; yk++) {
                if ((xk != x || yk != y) && sudoku.getCell(yk, xk) == sudoku.getCell(y, x)) return false;
            }
        }
        return true;
    }

    public static int[] nextone(SudokuBoard sudoku, int x, int y) {
        int[] next = new int[2];
        for (int k = x; k < 9; k++) {
            for (int j = y; j < 9; j++) {
                if (!sudoku.isSet(j, k)) {
                    next[0] = k;
                    next[1] = j;

                    return next;
                }
            }
            y = 0;
        }
        next[0] = -1;
        next[1] = -1;
        return next;
    }
}