package assignment6;

/**
 * Hashing function that uses the default implementation of Java
 *
 * @param <T> the data type to be hashed
 */
public class CustomHashFunction implements HashFunction<String>{
	/**
	 * Simple hash function based on md5
	 */
	public static final CustomHashFunction Var1 = new CustomHashFunction("Var1");
	/**
	 * Simple hash function based on sha-1
	 */
	public static final CustomHashFunction Var2 = new CustomHashFunction("Var2");
	/**
	 * Simple hash function based on sha-1
	 */
	public static final CustomHashFunction Var3 = new CustomHashFunction("Var3");
	
	private final String algorithm;
	public CustomHashFunction(String algorithm) {
		this.algorithm = algorithm;
	}
	
	/*
	 * (non-Javadoc)
	 * @see assignment6.HashFunction#hash(java.lang.Object)
	 */
	@Override
	public int hash(String input) {
		if(null==input) {
			return 0;
		}
		
		throw new IllegalStateException("The algorithm " + algorithm + " is not known!");
	}
	
	
}
