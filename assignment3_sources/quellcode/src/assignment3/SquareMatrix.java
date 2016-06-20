package assignment3;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * This class implements a data structure to store squared matrices (same amount of columns and rows)
 */
public class SquareMatrix {
    protected final double[][] values;

    protected SquareMatrix() {
        values = null;
    }

    /**
     * Generate an matrix contents of size NxN
     *
     * @param n      size of the matrix
     * @param method initialize it according to one of the following methods:</br>
     *               <ul>
     *               <li>Zero - F[i][j] = 0.0</li>
     *               <li>Plus - F[i][j] = i+j</li>
     *               <li>Minus - F[i][j] = i-j</li>
     *               <li>Random - F[i][j] = random value between -10 and 10</li>
     *               </ul>
     * @return a new (filled) double[][] array
     */
    public SquareMatrix(int n, InitStrategy method) {

        if (n <= 0) {
            throw new IllegalArgumentException("SquareMatrix size has to be >0");
        }
        if (method == null) {
            throw new IllegalArgumentException("Method must not be null");
        }

        values = new double[n][n];

        Random r = null;
        // iterate over each element and initialize it
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                switch (method) {
                    case Zero:
                        // nothing to do: values are zero-initialized already
                        return;
                    case Plus:
                        values[i][j] = i + j;
                        break;
                    case Minus:
                        values[i][j] = i - j;
                        break;
                    case Random:
                        if (r == null) {
                            // If not yet done: initialize with random numbers
                            r = new java.util.Random();
                        }
                        // nextDouble returns a number between 0.0 and 1.0
                        values[i][j] = r.nextDouble() * 20.0 - 10.0;
                        break;
                }
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        //StringBuilder verwenden, um mehrere Strings zu konkatenieren - ist schneller!
        StringBuilder sb = new StringBuilder();
        DecimalFormat d = new DecimalFormat("###0.0");
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (j > 0) {
                    sb.append('\t');
                }
                sb.append(d.format(get(i, j)));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * Matrix multiplication (between SquareMatrixes)
     *
     * @param other the other matrix instance
     * @return the result of the matrix multiplication
     */
    public SquareMatrix multiply(SquareMatrix other) {
        if (other == null) {
            throw new IllegalArgumentException("SquareMatrix other must not be null");
        }
        if (values.length != other.values.length) {
            throw new IllegalArgumentException("Dimensions do not match!");
        }
        SquareMatrix result = newEmptyInstance();
        for (int i = 0; i < size(); i++) {
            for (int k = 0; k < size(); k++) {
                // c_{i,k} = \sum_{j=0}^{n} a_{i,j} * b_{j,k}
                double sum = 0D;
                for (int j = 0; j < size(); j++) {
                    sum += get(i, j) * other.get(j, k);
                }
                result.set(i, k, sum);
            }
        }
        return result;
    }

    /**
     * Scalar multiplication (every value in matrix is multiplied by given scalar)
     *
     * @param alpha scaling factor
     * @return the result of the scalar multiplication
     */
    public SquareMatrix multiply(double alpha) {
        SquareMatrix result = newEmptyInstance();
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                result.set(i, j, alpha * get(i, j));
            }
        }
        return result;
    }

    /**
     * find the minimal value in matrix
     *
     * @return the minimum
     */
    public double min() {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                final double current = get(i, j);
                if (Double.isNaN(current)) {
                    continue;
                } else if (!(min <= current)) {
                    min = current;
                }
            }
        }
        return min;
    }

    /**
     * find the maximal value in matrix
     *
     * @return the maximum
     */
    public double max() {
        double max = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                final double current = get(i, j);
                if (Double.isNaN(current)) {
                    continue;
                } else if (!(max >= current)) {
                    max = current;
                }
            }
        }
        return max;
    }

    /**
     * Return the cell value in position i,j
     *
     * @param i index i
     * @param j index j
     * @return the cell value on index i and j
     */
    public double get(int i, int j) {
        return values[i][j];
    }

    /**
     * Set cell value at position i,j
     *
     * @param i   index i
     * @param j   index j
     * @param the new cell value
     */
    public void set(int i, int j, double value) {
        values[i][j] = value;
    }

    /**
     * return size of matrix (number of columns and rows) : size of 2x2 matrix = 2
     *
     * @return the size
     */
    public int size() {
        return values.length;
    }

    /**
     * create new EmptyMatrix with same size as matrix instance, which this method is called from
     *
     * @return an empty instance (zero initialized)
     */
    protected SquareMatrix newEmptyInstance() {
        return new SquareMatrix(size(), InitStrategy.Zero);
    }

    /**
     * initialization strategies:
     * <ul>
     * <li>Zero - m[i][j] = 0.0</li>
     * <li>Plus - m[i][j] = i+j</li>
     * <li>Minus - m[i][j] = i-j</li>
     * <li>Random - m[i][j] = random value between -10 and 10</li>
     * </ul>
     */
    public enum InitStrategy {
        Zero, Plus, Minus, Random
    }
}
