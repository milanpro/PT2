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
		if(null!=input) {
			int h; //Hashwert
			if (algorithm.equals("Var1")){ //Anfangsbuchstaben des inputs
				h = (int) input.charAt(0);
				if(h>96) h=h-97;
				else h = h-65;
			}
			else if (algorithm.equals("Var2")) //Länge des inputs
				h = input.length();

			else if(algorithm.equals("Var3")) { //Alle Zeichen des inputs in ASCII kodiert zusammenzählen
				h = 0;
				for (int i = 0; i < input.length(); i++) {
					h = h + (int) input.charAt(i);
				}
			}
			else throw new IllegalStateException("The algorithm " + algorithm + " is not known!");
			return h;
		}
		throw new IllegalStateException("The input " + input + " is not known!");
	}
	
	
}
