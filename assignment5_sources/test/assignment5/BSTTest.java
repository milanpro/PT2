package assignment5;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

public class BSTTest {
	
	
	@Test
	public void testPutIterative() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		bst.putIterative( 3, 3 );
		bst.putIterative( 4, -5 );
		bst.putIterative( 7, 0 );
		assertEquals( 3, bst.sizeRecursive() );
		assertEquals( Integer.valueOf( -5 ), bst.getRecursive( 4 ) );
	}
	
	@Test
	public void testGetRecursive() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		assertEquals( null, bst.getRecursive( 3 ) );
		bst.putRecursive( 3, 3 );
		bst.putRecursive( 4, -5 );
		bst.putRecursive( 7, 0 );
		assertEquals( Integer.valueOf( 3 ), bst.getRecursive( 3 ) );
	}
	
	
	@Test
	public void testHeightRecursive() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		assertEquals( -1, bst.heightRecursive() );
		bst.deleteRecursive( 2 );
		assertEquals( -1, bst.heightRecursive() );
		bst.putRecursive( 4, 0 );
		bst.putRecursive( 7, -3 );
		bst.putRecursive( 6, -1 );
		assertEquals( 2, bst.heightRecursive() );
	}
	
}
