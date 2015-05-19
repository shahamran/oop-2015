package oop.ex4.data_structures;

import java.util.Iterator;

public class BstTree implements BinaryTree {
	protected Node myRoot;
	protected int mySize;
	protected static final int NOT_FOUND = -1;
		
	public BstTree() {
		myRoot = null;
		mySize = 0;
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
		Node current = myRoot, last = null;
		while ( !(current == null) ) {
			if (current.getKey() == newValue) {
				return false;
			} else {
				last  = current;
				if (newValue < current.getKey()) {
					current = current.getLeft();
				} else {
				current = current.getRight();
				}
			}
		}
		mySize++;
		return last.setChild(newValue);
	}
	
	
	private boolean deleteSimpleNode(Node x) {
		if (x == null)
			return false;
		Node left,right,parent, toReplace;
		int key = x.getKey();
		left = x.getLeft();
		right = x.getRight();
		parent = x.getParent();
		
		if (left == null) {
			toReplace = right;
		} else if (right == null) {
			toReplace = left;
		} else {
			return false;
		}
		
		if (parent == null) {
			myRoot = toReplace;
		} else {
			if (key < parent.getKey()) {
				parent.setLeft(toReplace);
			} else {
				parent.setRight(toReplace);
			}
		}
		mySize--;
		return true;
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
		if (!deleteSimpleNode(nodeToDelete)) {
			Node succ = successor(nodeToDelete);
			deleteSimpleNode(succ);
			if (parent != null) {
				if (toDelete < parent.getKey()) {
					parent.setLeft(succ);
				} else {
					parent.setRight(succ);
				}
			} else {
				myRoot = succ;
			}
			succ.setLeft(left);
			succ.setRight(right);
		}
		return true;
	}
	
	/**
	 * Searches the tree for a node with a certain value.
	 * Returns it if found, otherwise returns the node where it should be added.
	 * @param searchVal The value to search for.
	 * @return The node with the given value if it's in the tree, otherwise the node that will be its
	 * parent if it is added.
	 */
	protected Node getNodeWithVal(int searchVal) {
		Node current = myRoot,last = null;
		while (current != null) {
			if (searchVal == current.getKey()) {
				return current;
			} else {
				last = current;
				if (searchVal < current.getKey()) {
					current = current.getLeft();
				} else {
					current = current.getRight();
				}
			}
		}
		return last;
	}
	
	public int contains(int searchVal) {
		int depth = 0;
		Node current = myRoot;
		while (current != null) {
			if (searchVal == current.getKey()) {
				return depth;
			} else if (searchVal > current.getKey()) {
				current = current.getRight();
			} else {
				current = current.getLeft();
			}
			depth++;
		}
		return NOT_FOUND;
	}
	
	public int size() {
		return mySize;
	}
	
	public static Node getMin(Node subTree) {
		if (subTree == null)
			return null;
		Node current = subTree, left = current.getLeft();
		while (left != null) {
			current = left;
			left = current.getLeft();
		}
		return current;
	}
	
	public static Node getMax(Node subTree) {
		if (subTree == null)
			return null;
		Node current = subTree, right = current.getRight();
		while (right != null) {
			current = right;
			right = current.getRight();
		}
		return current;
	}
	
	public static Node successor(Node x) {
		if (x == null)
			return null;
		
		if (x.getRight() != null) 
			return getMin(x.getRight());

		Node current = x, parent = x.getParent();
		while (parent != null) {
			if (parent.getLeft() == current) {
				return parent;
			} else {
				current = parent;
				parent = current.getParent();
			}
		}
		return null;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new BstIterator(myRoot);
	}
}
