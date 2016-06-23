package assignment5;

public class WordPair {
	protected int leftIndex;
	protected int rightIndex;
	
	public WordPair(int left, int right){
		leftIndex = left;
		rightIndex = right;
	}
	
	public int getLeftIndex() {
		return leftIndex;
	}

	public int getRightIndex() {
		return rightIndex;
	}


	@Override
	public String toString() {
		return "WordPair [" + leftIndex + ", " + rightIndex + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WordPair other = (WordPair) obj;
		if (leftIndex != other.leftIndex)
			return false;
		if (rightIndex != other.rightIndex)
			return false;
		return true;
	}		
}
