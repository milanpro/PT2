package assignment4;

/**
 * @author Annika Baldi
 * @author Milan Proell
 */
public class SudokuBoard {
    private int[][] cells;

    /**
     * creates a new Sudoku board
     */
    public SudokuBoard() {
        this(9);
    }

    private SudokuBoard(int size) {
        cells = new int[size][size];
    }

    SudokuBoard(int[][] s) {
        this(s.length);

        for (int i = 0; i < size(); i++) {
            if (s[i].length != size()) {
                throw new IllegalArgumentException("sudoku needs to be quadratic");
            }
            System.arraycopy(s[i], 0, cells[i], 0, size());
        }
    }

    /**
     * create a copy of the playboard
     *
     * @return the copy
     */
    public SudokuBoard copy() {
        return new SudokuBoard(cells);
    }

    /**
     * gets the value of the cell
     *
     * @param row
     * @param col
     * @return the value
     */
    public int getCell(int row, int col) {
        return cells[row][col];
    }

    /**
     * checks whether the cell is set, or not
     *
     * @param row
     * @param col
     * @return
     */
    public boolean isSet(int row, int col) {
        return getCell(row, col) != 0;
    }

    /**
     * removes the entry of the cell
     *
     * @param row
     * @param col
     */
    public void removeValue(int row, int col) {
        cells[row][col] = 0;
    }

    /**
     * sets the value of the cell
     *
     * @param value
     * @param row
     * @param col
     */
    public void setCell(int value, int row, int col) {
        if (value < 1 || value > size() || row < 0 || row > size() - 1 || col < 0 || col > size() - 1) {
            throw new IllegalArgumentException();
        }
        cells[row][col] = value;
    }

    /**
     * gets the size of the sudoku
     *
     * @return the size
     */
    public int size() {
        return cells.length;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder fields = new StringBuilder(2 * size() * size());
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                fields.append(' ');
                if (cells[i][j] > 0) {
                    fields.append(cells[i][j]);
                } else {
                    fields.append('?');
                }
                if (j < size() - 1) {
                    if (j % 3 == 2) {
                        fields.append(" |");
                    }
                } else {
                    fields.append(" \n");
                }
            }
            if (i < size() - 1 && i % 3 == 2) {
                fields.append("-------+-------+-------\n");
            }
        }
        return fields.toString();
    }

}