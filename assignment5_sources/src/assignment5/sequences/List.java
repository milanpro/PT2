package assignment5.sequences;

import java.util.NoSuchElementException;

/**
 * A special sequence which supports random access to it's elements.
 *
 * @param <T> The type of elements stored in this sequence.
 */
public interface List<T> extends Sequence<T> {
    /**
     * Insert an element at the given index.
     *
     * <p>The new element receives the given index. All elements with indices larger than the given index have their
     * indices increased by one.</p>
     *
     * @param index The index at which to insert the element.
     * @param element The element to be added.
     * @throws IndexOutOfBoundsException If the index is less than zero or adding the element at the given index would
     * leave a 'gap' in the list (that is, if the index is larger than it's size).
     */
    void insert(int index, T element);

    /**
     * Change the element at the given index.
     *
     * <p>All other elements must remain unaffected (their indices must not change).</p>
     *
     * @param index The index who's element should be updated.
     * @param element The element to place at the given index.
     * @return The element previously at the given index.
     * @throws NoSuchElementException if there is no element with the given index.
     */
    T set(int index, T element);

    /**
     * Retrieve the element at the given index.
     *
     * @param index The index of the element to retrieve.
     * @return The element at the given index.
     * @throws NoSuchElementException if there is no element with the given index.
     */
    T get(int index);

    /**
     * Removes the element at given index.
     *
     * <p>All elements with indices larger than the given index have their indices decreased by one.</p>
     *
     * @param index The index of the element to remove.
     * @return The element previously present at the given index.
     * @throws NoSuchElementException if there is no element with the given index.
     */
    T remove(int index);
}
