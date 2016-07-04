package assignment6;

import assignment6.HashFunction;

/**
 * class for storing String in a bloom filter
 *
 */
public class StringBloomfilter {
	private final boolean[] bits;
	private final HashFunction<String>[] hashFunctions;
	private int numberElements;
	
	/**
	 * creates a new BloomsFilter for strings, based on the specified hash functions
	 * @param m size of the bit array
	 * @param hashFunctions the hash functions
	 */
	@SafeVarargs
	public StringBloomfilter(int m, HashFunction<String>... hashFunctions) {
		if(m < 1 || hashFunctions==null || hashFunctions.length <= 0) {
			throw new IllegalArgumentException();
		}
		this.hashFunctions = hashFunctions;
		bits = new boolean[m];
	}
	/**
	 * adds v to the filter
	 * @param v the element to add
	 */
	public void add(String v) {
	}
	/**
	 * checks if w might be element of this collection
	 * @param w the element to be checked
	 */
	public boolean mightContain(String w) {
		return true;
	}
	/**
	 * gets the number of strings added to the filter
	 * @return the number of string elements n
	 */
	public int elementCount() {
		return numberElements;
	}
}
