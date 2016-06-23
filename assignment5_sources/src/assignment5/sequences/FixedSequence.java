package assignment5.sequences;

/**
 * A special sequence which limits the number of elements it may contain at any one time.
 *
 * <p>All operations on this sequence, which would make it's size exceed it's maximum size, must throw an
 * {@link IllegalStateException} instead of modifying the sequence or throwing any other exception.</p>
 *
 * @param <T> The type of elements stored in this sequence.
 */
public interface FixedSequence<T> extends Sequence<T> {
    /**
     * @return The maximum number of elements in the sequence.
     */
    int maximumSize();
}
