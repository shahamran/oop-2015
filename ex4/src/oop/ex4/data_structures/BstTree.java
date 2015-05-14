package oop.ex4.data_structures;

import java.util.Iterator;

public class BstTree implements Iterable<Integer>,BinaryTree {
	protected Node myRoot;
	protected int mySize;
		
	public BstTree() {
		myRoot = null;
	}
	
	public BstTree(BstTree bstTree) {
		this();
		for (int val : bstTree) {
			add(val);
		}
	}
	
	public Node getRoot() {
		return myRoot;
	}
	
	public boolean add(int newValue) {
		if (myRoot == null) {
			myRoot = new Node(newValue);
			mySize++;
			return true;
		}
		Node current = myRoot;
		boolean shouldStop = false;
		while (!shouldStop) {
			if (newValue < current.getKey()) {
				if (current.getLeft() != null) {
					current = current.getLeft();
				} else {
					shouldStop = true;
				}
			} else {
				if (current.getRight() != null) {
					current = current.getRight();
				} else {
					shouldStop = true;
				}
			}
		}
		
		if (current.setNewChild(newValue)) {
			mySize++;
			return true;
		} else {
			return false;
		}
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
	
	public static Node getMin(Node subTree) {
		if (subTree == null)
			return null;
		Node current = subTree;
		while (current.getLeft() != null) {
			current = current.getLeft();
		}
		return current;
	}
	
	public static Node getMax(Node subTree) {
		if (subTree == null)
			return null;
		Node current = subTree;
		while (current.getRight() != null) {
			current = current.getRight();
		}
		return current;
	}
	
	public static Node successor(Node x) {
		if (x == null)
			return null;
		
		if (x.getRight() != null) 
			return getMin(x.getRight());

		Node current = x;
		while (current.getParent() != null) {
			if (current.getParent().getLeft() == current) {
				return current.getParent();
			} else {
				current = current.getParent();
			}
		}
		return null;
		
	}

	@Override
	public Iterator<Integer> iterator() {
		return new BstIterator(myRoot);
	}
}
