package assignment5;

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {

    private Node root;

    public BST() {
        root = null;
    }

    public static void main(String[] args) {
        System.out.println("This is a BST!");
    }

    public void putRecursive(Key key, Value val) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (val == null) {
            deleteRecursive(key);
            return;
        }
        root = putRecursive(root, key, val);
    }

    private Node putRecursive(Node n, Key key, Value val) {
        if (n == null) {
            return new Node(key, val, 1);
        }
        int cmp = key.compareTo(n.getKey());
        if (cmp < 0) {
            n.setLeft(putRecursive(n.getLeft(), key, val));
        } else if (cmp > 0) {
            n.setRight(putRecursive(n.getRight(), key, val));
        } else {
            n.setValue(val);
        }
        n.setN(1 + sizeRecursive(n.getLeft()) + sizeRecursive(n.getRight()));
        return n;
    }

    public void putIterative(Key key, Value val) {

    }

    public Value getRecursive(Key key) {
        return null;
    }

    public Value getIterative(Key key) {
        Node n = root;
        while (n != null) {
            int cmp = key.compareTo(n.getKey());
            if (cmp < 0) {
                n = n.getLeft();
            } else if (cmp > 0) {
                n = n.getRight();
            } else {
                return n.getValue();
            }
        }
        return null;
    }

    public void deleteRecursive(Key key) {
        if (key == null) {
            throw new NullPointerException();
        }
        root = deleteRecursive(root, key);
    }

    private Node deleteRecursive(Node n, Key key) {
        if (n == null) {
            return null;
        }
        int cmp = key.compareTo(n.getKey());
        if (cmp < 0) {
            n.setLeft(deleteRecursive(n.getLeft(), key));
        } else if (cmp > 0) {
            n.setRight(deleteRecursive(n.getRight(), key));
        } else {
            if (n.getRight() == null) {
                return n.getLeft();
            }
            if (n.getLeft() == null) {
                return n.getRight();
            }
            Node t = n;
            n = minRecursive(t.getRight());
            n.setRight(deleteMinRecursive(t.getRight()));
            n.setLeft(t.getLeft());
        }
        n.setN(1 + sizeRecursive(n.getLeft()) + sizeRecursive(n.getRight()));
        return n;
    }

    public void deleteMinRecursive() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        root = deleteMinRecursive(root);
    }

    private Node deleteMinRecursive(Node n) {
        if (n.getLeft() == null) {
            return n.getRight();
        }
        n.setLeft(deleteMinRecursive(n.getLeft()));
        n.setN(1 + sizeRecursive(n.getLeft()) + sizeRecursive(n.getRight()));
        return n;
    }

    public void deleteMaxRecursive() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        root = deleteMaxRecursive(root);
    }

    private Node deleteMaxRecursive(Node n) {
        if (n.getRight() == null) {
            return n.getLeft();
        }
        n.setRight(deleteMaxRecursive(n.getRight()));
        n.setN(1 + sizeRecursive(n.getLeft()) + sizeRecursive(n.getRight()));
        return n;
    }

    public boolean isEmpty() {
        return sizeRecursive() == 0;
    }

    public int sizeRecursive() {
        return sizeRecursive(root);
    }

    private int sizeRecursive(Node n) {
        if (n == null) {
            return 0;
        } else {
            return n.getN();
        }
    }

    public Key minRecursive() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return minRecursive(root).getKey();
    }

    private Node minRecursive(Node n) {
        if (n.getLeft() == null) {
            return n;
        } else {
            return minRecursive(n.getLeft());
        }
    }

    public Key maxRecursive() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return maxRecursive(root).getKey();
    }

    private Node maxRecursive(Node n) {
        if (n.getRight() == null) {
            return n;
        } else {
            return maxRecursive(n.getRight());
        }
    }

    public int heightRecursive() {
        return 0;
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

        public Node(Key key, Value val, int N) {
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

        public void setValue(Value val) {
            this.val = val;
        }

        public int getN() {
            return N;
        }

        public void setN(int N) {
            this.N = N;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

    }

}
