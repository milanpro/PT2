package assignment4;

import org.junit.Test;

import java.util.BitSet;

import static org.junit.Assert.assertEquals;

public class SudokuSolverTest {

    public void check(SudokuBoard s) {
        // each row and each column contains the numbers from 1-9
        for (int i = 0; i < 9; i++) {
            BitSet filledRow = new BitSet(9);
            BitSet filledCol = new BitSet(9);
            for (int j = 0; j < 9; j++) {
                filledRow.set(s.getCell(i, j) - 1);
                filledCol.set(s.getCell(j, 1) - 1);
            }
            assertEquals(filledRow.cardinality(), 9);
            assertEquals(filledCol.cardinality(), 9);
        }

        // each 3x3 box contains the numbers from 1-9
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                BitSet filledBox = new BitSet(9);
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        filledBox.set(s.getCell(i + k, j + l) - 1);
                    }
                }
                assertEquals(filledBox.cardinality(), 9);
            }
        }
    }

    @Test
    public void createRandomSolvedSudokuTest() {
        SudokuSolver solver = new SudokuSolver();

        SudokuBoard b = SudokuSolver.createSudokuRiddle(9);
        System.out.println(b);
        SudokuSolver.solve(b);
        System.out.println(b);
        check(b);
    }

    @Test
    public void testSolve() {
        int[][] riddle = {{8, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 3, 6, 0, 0, 0, 0, 0}, {0, 7, 0, 0, 9, 0, 2, 0, 0},
                {0, 5, 0, 0, 0, 7, 0, 0, 0}, {0, 0, 0, 0, 4, 5, 7, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 3, 0},
                {0, 0, 1, 0, 0, 0, 0, 6, 8}, {0, 0, 8, 5, 0, 0, 0, 1, 0}, {0, 9, 0, 0, 0, 0, 4, 0, 0}};

        int[][] solution = {{8, 1, 2, 7, 5, 3, 6, 4, 9}, {9, 4, 3, 6, 8, 2, 1, 7, 5},
                {6, 7, 5, 4, 9, 1, 2, 8, 3}, {1, 5, 4, 2, 3, 7, 8, 9, 6}, {3, 6, 9, 8, 4, 5, 7, 2, 1},
                {2, 8, 7, 1, 6, 9, 5, 3, 4}, {5, 2, 1, 9, 7, 4, 3, 6, 8}, {4, 3, 8, 5, 2, 6, 9, 1, 7},
                {7, 9, 6, 3, 1, 8, 4, 5, 2}};

        SudokuBoard hardSudoku = new SudokuBoard(riddle);
        SudokuBoard solvedSudoku = new SudokuBoard(solution);

        SudokuSolver solver = new SudokuSolver();
        SudokuSolver.solve(hardSudoku);

        assertEquals(hardSudoku.toString(), solvedSudoku.toString());
    }
}