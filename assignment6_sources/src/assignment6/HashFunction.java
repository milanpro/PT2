package assignment6;

/**
 * The abstract data type for a hashing function
 *
 * @param <T> the data type to be hashed
 */
public interface HashFunction<T> {
	/**
	 * gets the hash value for the input
	 * @param input the input to be hashed
	 * @return the hash value
	 */
	public int hash(T input);
}
