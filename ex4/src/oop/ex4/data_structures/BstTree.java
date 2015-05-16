package oop.ex4.data_structures;

import java.util.Iterator;

public class BstTree implements Iterable<Integer>, BinaryTree {
	protected Node myRoot;
	protected int mySize;
		
	public BstTree() {
		myRoot = null;
	}
	
	public BstTree(int[] data) {
		this();
		if (data == null)
			return;
		for (int val : data) {
			add(val);
		}
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
		Node addTo = getNodeWithVal(newValue);
		if (addTo.getKey() == newValue) {
			return false;
		} else {
			return addTo.setChild(new Node(newValue,addTo));
		}
	}
	
	public boolean delete(int toDelete) {
		Node nodeToDelete = getNodeWithVal(toDelete);
		if (nodeToDelete == null)
			return false;
		if (nodeToDelete.getKey() != toDelete)
			return false;
		Node parent = nodeToDelete.getParent(),
			 left = nodeToDelete.getLeft(),
			 right = nodeToDelete.getRight();
		if (left == null) {
			if (parent == null) {
				myRoot = right;
			} else {
				if (toDelete < parent.getKey()) {
					parent.setLeft(right);
				} else {
					parent.setRight(right);
				}
			}
		} else if (right == null) {
			if (parent == null) {
				myRoot = left;
			} else {
				if (toDelete < parent.getKey()) {
					parent.setLeft(left);
				} else {
					parent.setRight(left);
				}
			}
		} else {
			Node succ = successor(nodeToDelete);
			delete(succ.getKey());
			mySize++;
			succ.setParent(parent);
			succ.setLeft(left);
			succ.setRight(right);
			if (parent != null) {
				parent.setChild(succ);
			}
		}
		mySize--;
		return true;
	}
	
	/**
	 * 
	 * @param searchVal
	 * @return
	 */
	protected Node getNodeWithVal(int searchVal) {
		Node current = myRoot,last = myRoot;
		while (current != null) {
			if (searchVal == current.getKey()) {
				return current;
			} else if (searchVal < current.getKey()) {
				last = current;
				current = current.getLeft();
			} else {
				last = current;
				current = current.getRight();
			}
		}
		return last;
	}
	
	public int contains(int searchVal) {
		Node searchNode = getNodeWithVal(searchVal);
		if (searchNode.getKey() == searchVal) {
			
		}
		return 0;
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
