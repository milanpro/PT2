package assignment5;

import java.util.NoSuchElementException;
/*
1.
..........P..........
......____|____.....
......|........|.....
......O........R.....
...___|..............
...|.................
...G.................
.__|______...........
.|........|..........
.A........M..........
.|__...___|___.......
....|..|......|......
....E..I......N......


Ab dem Einfügen von A ist die AVL Bedingung verletzt.

Um den Baum zu balancieren könnte man M als Wurzel wählen.

3 und 4 funktioniert nicht.

*/

public class BST<Key extends Comparable<Key>, Value> {

	public static void main(String[] args) {
		System.out.println("This is a BST!");
	}

	private class Node {

		// sorted by key
		private Key key;

		// associated data
		private Value val;

		// number of nodes in subtree
		private int N;

		// left and right subtrees
		private Node left, right;

		public Node( Key key, Value val, int N ) {
			this.key = key;
			this.val = val;
			this.N = N;
		}

		public Key getKey() {
			return key;
		}

		public Value getValue() {
			return val;
		}
		public void setValue( Value val ) {
			this.val = val;
		}

		public int getN() {
			return N;
		}
		public void setN( int N ) {
			this.N = N;
		}

		public Node getLeft() {
			return left;
		}
		public void setLeft( Node left ) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}
		public void setRight( Node right ) {
			this.right = right;
		}

	}

	private Node root;

	public BST() {
		root = null;
	}

	public void putRecursive( Key key, Value val ) {
		if ( key == null ) {
			throw new NullPointerException();
		}
		if ( val == null ) {
			deleteRecursive( key );
			return;
		}
		root = putRecursive( root, key, val );
	}
	private Node putRecursive( Node n, Key key, Value val ) {
		if ( n == null ) {
			return new Node( key, val, 1 );
		}
		int cmp = key.compareTo( n.getKey() );
		if ( cmp < 0 ) {
			n.setLeft( putRecursive( n.getLeft(), key, val ) );
		} else if ( cmp > 0 ) {
			n.setRight( putRecursive( n.getRight(), key, val ) );
		} else {
			n.setValue( val );
		}
		n.setN( 1 + sizeRecursive( n.getLeft() ) + sizeRecursive( n.getRight() ) );
		return n;
	}

	public void putIterative( Key key, Value val ) {
		//Ausnahmefälle
		if ( key == null ) {
			throw new NullPointerException();
		}
		if( root==null ) {
			root = new Node(key, val, 1);
			return;
		}

		int depth = 0;
		Node n = root;
		while ( true ) {
			int cmp = key.compareTo( n.getKey() );
			if ( cmp < 0 ) { //Key ist kleiner, also im Linken Knoten weiterarbeiten
				n.setN(n.getN()+1); //erhöhen von N, da erwartungsgemäß baum um eins größer wird
				depth++;
				if(n.getLeft()==null){
					n.setLeft(new Node(key, val, 1)); //einfügen des neuen Wertes
					return;
				}
				else n = n.getLeft();
			} else if ( cmp > 0 ) { //Key ist größer, also im Rechten Knoten weiterarbeiten
				n.setN(n.getN()+1); //erhöhen von N, da erwartungsgemäß baum um eins größer wird
				depth++;
				if(n.getRight()==null){
					n.setRight(new Node(key, val, 1)); // einfügen des neuen Wertes
					return;
				}
				else n = n.getRight();
			} else { // Wenn der Key derselbe ist
				n.setValue( val ); //ersetze Wert
				n = root;
				for(int i=0; i<depth;i++){ //setze alle Werte N der oberen Knoten zurück
					cmp = key.compareTo( n.getKey() );
					if ( cmp < 0 ){
						n.setN(n.getN()-1);
						n = n.getLeft();
					}
					else if ( cmp > 0 ){
						n.setN(n.getN()-1);
						n = n.getRight();
					}
				}
				return;
			}
		}
	}


	public Value getRecursive( Key key ) {
		return getRecursive(root,key); //starte mit der Wurzel
	}
	private Value getRecursive(Node n, Key key){
		if(n==null) return null; //wenn Knoten leer dann Wert leer
		int cmp = key.compareTo( n.getKey() );
		if ( cmp < 0 ) {
			return getRecursive(n.getLeft(), key);
		} else if ( cmp > 0 ) {
			return getRecursive(n.getRight(), key);
		} else {
			return n.getValue(); //sobald key gefunden, gib Wert zurück
		}
	}

	public Value getIterative( Key key ) {
		Node n = root;
		while ( n != null ) {
			int cmp = key.compareTo( n.getKey() );
			if ( cmp < 0 ) {
				n = n.getLeft();
			} else if ( cmp > 0 ) {
				n = n.getRight();
			} else {
				return n.getValue();
			}
		}
		return null;
	}

	public void deleteRecursive( Key key ) {
		if ( key == null ) {
			throw new NullPointerException();
		}
		root = deleteRecursive( root, key );
	}
	private Node deleteRecursive( Node n, Key key ) {
		if ( n == null ){
			return null;
		}
		int cmp = key.compareTo( n.getKey() );
		if ( cmp < 0 ) {
			n.setLeft( deleteRecursive( n.getLeft(), key ) );
		} else if ( cmp > 0 ) {
			n.setRight( deleteRecursive( n.getRight(), key) );
		}
		else {
			if ( n.getRight() == null) {
				return n.getLeft();
			}
			if ( n.getLeft() == null) {
				return n.getRight();
			}
			Node t = n;
			n = minRecursive( t.getRight() );
			n.setRight( deleteMinRecursive( t.getRight() ) );
			n.setLeft( t.getLeft() );
		}
		n.setN( 1 + sizeRecursive( n.getLeft() ) + sizeRecursive( n.getRight() ) );
		return n;
	}

	public void deleteMinRecursive() {
		if ( isEmpty() ){
			throw new NoSuchElementException();
		}
		root = deleteMinRecursive( root );
	}
	private Node deleteMinRecursive( Node n ) {
		if ( n.getLeft() == null ) {
			return n.getRight();
		}
		n.setLeft( deleteMinRecursive( n.getLeft() ) );
		n.setN( 1 + sizeRecursive( n.getLeft() ) + sizeRecursive( n.getRight() ) );
		return n;
	}

	public void deleteMaxRecursive() {
		if ( isEmpty() ) {
			throw new NoSuchElementException();
		}
		root = deleteMaxRecursive( root );
	}
	private Node deleteMaxRecursive( Node n ) {
		if ( n.getRight() == null ) {
			return n.getLeft();
		}
		n.setRight( deleteMaxRecursive( n.getRight() ) );
		n.setN( 1 + sizeRecursive( n.getLeft() ) + sizeRecursive( n.getRight() ) );
		return n;
	}

	public boolean isEmpty() {
		return sizeRecursive() == 0;
	}

	public int sizeRecursive() {
		return sizeRecursive( root );
	}
	private int sizeRecursive( Node n ) {
		if ( n == null ) {
			return 0;
		} else {
			return n.getN();
		}
	}

	public Key minRecursive() {
		if ( isEmpty() ) {
			throw new NoSuchElementException();
		}
		return minRecursive( root ).getKey();
	}
	private Node minRecursive( Node n ) {
		if ( n.getLeft() == null ) {
			return n;
		} else {
			return minRecursive( n.getLeft());
		}
	}

	public Key maxRecursive() {
		if ( isEmpty() ) {
			throw new NoSuchElementException();
		}
		return maxRecursive( root ).getKey();
	}
	private Node maxRecursive( Node n ) {
		if ( n.getRight() == null ) {
			return n;
		} else {
			return maxRecursive( n.getRight() );
		}
	}

	public int heightRecursive() {
		if (root == null) return -1; //falls baum leer, höhe = -1
		else {
			return heightRecursive(root); //starte mit wurzel
		}
	}
	private int heightRecursive(Node n){
		Node left = n.getLeft();
		Node right = n.getRight();
		if(left!=null && right!=null) return 1 + heightRecursive(left) + heightRecursive(right); //beide teilbäume sind nicht leer -> rekursiv in beiden weiterzählen
		else if (left==null&&right==null) return 0; //am ende angekommen
		else if (left==null) return 1 + heightRecursive(right); //rechter Teilbaum nicht leer
		else return 1 + heightRecursive(left); //öinker teilbaum nicht leer
	}

}
