package assignment5;

public class Shard {
	private String word;
	public static int splitCount;
	
	/**
	 * Creates a shard
	 * @param s the inscription on the shard
	 */
	public Shard(String s){
		word = s;
	}
	
	/**
	 * get the left part of the shard
	 * @param split The index on which to split the shard
	 * @return A shard containing the letters up to (excluding) split
	 */
	public Shard getLeftOf(int split){
		splitCount++;
		return new Shard(word.substring(0, split));
	}
	
	/**
	 * get the right part of the shard
	 * @param split The index of on which to split the shard
	 * @return A shard containing the letters after (including) split
	 */
	public Shard getRightOfIncluding(int split){
		splitCount++;
		return new Shard(word.substring(split));
	}
	
	/**
	 * Access the first character of the shard
	 * @return the first character of the shard
	 */
	public char getFirstChar(){
		return word.charAt(0);
	}
	
	/**
	 * Finds out whether a shard is empty
	 * @return true if the shard does not contain any inscription, false otherwise
	 */
	public boolean isEmpty(){
		return (word.length() == 0);
	}
	
	/**
	 * finds out whether the inscription on a shard is symmetrical
	 * @return true if the inscription is symmetrical, false otherwise
	 */
	public boolean isSymmetric(){
		String reversed = new StringBuilder(word).reverse().toString();
		return word.equals(reversed);
	}
	
	/**
	 * Get A shard containing the reverse inscription
	 * @return A shard containing the reverse inscription
	 */
	public Shard reverseShard(){
		String reversed = new StringBuilder(word).reverse().toString();
		return new Shard(reversed);
	}
	
	/**
	 * Get the length of the inscription
	 * @return the length of the inscription on the shard
	 */
	public int length(){
		return word.length();
	}
	
	//Functions below are only used for testing.
	public static int getSplitCount(){
		return splitCount;
	}
	/*
	public static void resetSplitCount(ArcheologyTest.Friend friend){
		friend.hashCode();
		splitCount = 0;
	}
	*/
}
