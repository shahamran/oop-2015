package oop.ex4.data_structures;

public class BstTree implements BinaryTree {
	protected Node root;
	protected int mySize;
	
	public BstTree() {
		root = null;
	}
	
	public BstTree(BstTree bstTree) {
		this();
	}
	
	public Node getRoot() {
		return root;
	}
	
	public boolean add(int newValue) {
		if (root == null) {
			root = new Node(newValue);
			mySize++;
			return true;
		} else {
			
		}
		return false;
	}
	
	public boolean delete(int toDelete) {
		return false;
	}
	
	public int contains(int searchVal) {
		return -1;
	}
	
	public int size() {
		return mySize;
	}
	
	protected Node min(Node subTree) {
		if (subTree == null)
			return null;
		Node current = subTree;
		while (current.getLeft() != null) {
			current = current.getLeft();
		}
		return current;
	}
	
	protected Node max(Node subTree) {
		if (subTree == null)
			return null;
		Node current = subTree;
		while (current.getRight() != null) {
			current = current.getRight();
		}
		return current;
	}
	
	protected Node successor(Node x) {
		return null;
	}
}
