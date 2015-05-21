package oop.ex4.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

public class BstTree implements Iterable<Integer> {
	protected Node myRoot;
	protected int mySize;
	protected static final int NOT_FOUND = -1;
	
	/**
	 * The default constructor.
	 */
	public BstTree() {
		myRoot = null;
		mySize = 0;
	}
	
	/**
	 * A constructor that builds the tree by adding the elements in the input array one by
	 * one. If a value appears more that once in the list, only the first appearance is added.
	 * 
	 * @param data the values to add to the tree.
	 */
	public BstTree(int[] data) {
		this();
		if (data == null)
			return;
		for (int val : data) {
			add(val);
		}
	}
	
	/**
	 * A copy constructor that creates a deep copy of the given BstTree.
	 * 
	 * @param bstTree a BST tree.
	 */
	public BstTree(BstTree bstTree) {
		this();
		for (int val : bstTree) {
			add(val);
		}
	}

	/**
	 * Add a new node with the given key to the tree.
	 * 
	 * @param newValue the value of the new node to add.
	 * @return true if the value to add is not already in the tree and it was successfully added,
	 * false otherwise.
	 */
	public boolean add(int newValue) {
		if (myRoot == null) { // Special case for an empty tree.
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
		if (last.setChild(newValue)) {
			mySize++;
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Attempts to delete a node.
	 * Returns the following:  
	 * If the deleted node isn't found - returns null.
	 * If the deleted node has no children - returns its parent.
	 * If the deleted node has one child - returns the child.
	 * If the deleted node has two children - returns null.
	 */
	private Node deleteSimpleNode(Node x) {
		if (x == null)
			return null;
		Node left, right, parent, toReplace, toReturn;
		int key = x.getKey();
		left = x.getLeft();
		right = x.getRight();
		parent = x.getParent();
		
		if (left == null) {
			toReplace = right;
		} else if (right == null) {
			toReplace = left;
		} else {
			return null; // This means the node has 2 children.
		}
		// From here on, the deletion should succeed.
		if (parent == null) {
			toReturn = toReplace;
			myRoot = toReplace; // Update the root if it is being deleted.
		} else {
			toReturn = parent;
			if (key < parent.getKey()) {
				parent.setLeft(toReplace);
			} else {
				parent.setRight(toReplace);
			}
		}
		mySize--;
		return toReturn;
	}
	
	/*
	 * Attempts to delete a node with a given value.
	 * If there is no node with the given value - returns null.
	 * If the deleted node was the only node in the tree (the root) - returns null.
	 * If the deleted node had one child - returns it.
	 * If the deleted node had two children - returns its replacement (the successor).
	 */
	protected Node deleteAndGet(int toDelete) {
		Node nodeToDelete = getNodeWithVal(toDelete);
		if (nodeToDelete == null) {
			return null;
		}
		Node deleted = deleteSimpleNode(nodeToDelete); // Tries to delete the node.
		if (mySize == 0)
			return null;
		if (deleted == null) { // This means the node has 2 children
			Node succ = successor(nodeToDelete);
			deleteSimpleNode(succ); // Removes the successor from its place
			replaceXwithY(deleted, succ);
			return succ;
		}
		return deleted;
	}
	
	/*
	 * Replaces a given node with another - which means - set the new node as a child of the
	 * old-node's parent, set the old node's children as the new node's children.
	 * NOTE: This removes the reference to the old node in the structure.
	 */
	private boolean replaceXwithY(Node toReplace, Node replacement) {
		if (toReplace == null || replacement == null)
			return false;
		Node parent = toReplace.getParent(), 
			 left = toReplace.getLeft(), right = toReplace.getRight();
		int key = replacement.getKey();
		if (parent == null) {
			myRoot = replacement;
		} else {
			if (key < parent.getKey()) {
				parent.setLeft(replacement);
			} else {
				parent.setRight(replacement);
			}
		}
		replacement.setLeft(left);
		replacement.setRight(right);
		return true;
	}
	
	/**
	 * Removes the node with the given value from the tree, if it exists.
	 * 
	 * @param toDelete the value to remove from the tree.
	 * @return true if the give value was found and deleted, false otherwise.
	 */
	public boolean delete(int toDelete) {
		if (contains(toDelete) == NOT_FOUND) {
			return false;
		}
		// See the deleteAndGet description for details.
		return deleteAndGet(toDelete) != null || mySize == 0;
	}
	
	/**
	 * Searches the tree for a node with a certain value.
	 * Returns it if found, otherwise returns null.
	 * @param searchVal The value to search for.
	 * @return The node with the given value if it's in the tree, null otherwise
	 */
	protected Node getNodeWithVal(int searchVal) {
		Node current = myRoot;
		while (current != null) {
			if (searchVal == current.getKey()) {
				return current;
			} else {
				if (searchVal < current.getKey()) {
					current = current.getLeft();
				} else {
					current = current.getRight();
				}
			}
		}
		return null;
	}
	
	/**
	 * Check whether the tree contains the given input value.
	 * 
	 * @param searchVal the value to search for.
	 * @return the depth of the node (0 for the root) with the given value if it was found in
	 * the tree, -1 otherwise.
	 */
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
	
	/**
	 * @return the number of nodes in the tree.
	 */
	public int size() {
		return mySize;
	}
	
	/**
	 * @param subTree The root of a subtree.
	 * @return The min node in the given subtree.
	 */
	protected static Node getMin(Node subTree) {
		if (subTree == null)
			return null;
		Node current = subTree, left = current.getLeft();
		while (left != null) {
			current = left;
			left = current.getLeft();
		}
		return current;
	}
	
	/**
	 * @param subTree The root of a subtree.
	 * @return The max node in the given subtree.
	 */
	protected static Node getMax(Node subTree) {
		if (subTree == null)
			return null;
		Node current = subTree, right = current.getRight();
		while (right != null) {
			current = right;
			right = current.getRight();
		}
		return current;
	}
	
	/**
	 * Returns the successor of a given node.
	 * @param x The node to check.
	 * @return The node after it in the tree.
	 */
	protected static Node successor(Node x) {
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
	/**
	 * Returns an iterator that iterates over the BST in ascending order.
	 */
	public Iterator<Integer> iterator() {
		/**
		 * A local Iterator class on a BST tree.
		 * @author ransha
		 */
		class BstIterator implements Iterator<Integer> {
			Node current;
			
			BstIterator(Node root) {
				if (root == null) {
					current = null;
				} else {
					// The first node when iterating (in-order) is the minimum value of this tree.
					current = BstTree.getMin(root);
				}
			}

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public Integer next() {
				if (!hasNext())
					throw new NoSuchElementException();
				int val = current.getKey();
				// Switch to the next node in the tree.
				current = BstTree.successor(current);
				return val;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		}
		// Local class ends HERE.
		return new BstIterator(myRoot);
	}
}