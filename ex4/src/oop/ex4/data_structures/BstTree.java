package oop.ex4.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

public class BstTree implements Iterable<Integer> {
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
	
	/**
	 * Add a new node with the given key to the tree.
	 * 
	 * @param newValue the value of the new node to add.
	 * @return true if the value to add is not already in the tree and it was successfully added,
	 * false otherwise.
	 */
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
	
	/*
	 * Attempts to delete a node.
	 * Will return a value (other than null) if the node exists and it has no more than ONE child.
	 * Returns null if the node doesn't exists or it has 2 children.
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
	 * Returns the node
	 */
	protected Node deleteAndGet(int toDelete) {
		Node nodeToDelete = getNodeWithVal(toDelete);
		if (nodeToDelete == null) {
			return null;
		} else if (nodeToDelete.getKey() != toDelete) {
			return null;
		}
		
		Node parent = nodeToDelete.getParent(),
			 left = nodeToDelete.getLeft(),
			 right = nodeToDelete.getRight();
		Node deleted = deleteSimpleNode(nodeToDelete); // Tries to delete the node.
		if (mySize == 0)
			return null;
		if (deleted == null) { // This means the node has 2 children
			deleted = successor(nodeToDelete);
			deleteSimpleNode(deleted);
			if (parent != null) {
				if (toDelete < parent.getKey()) {
					parent.setLeft(deleted);
				} else {
					parent.setRight(deleted);
				}
			} else {
				myRoot = deleted;
			}
			deleted.setLeft(left);
			deleted.setRight(right);
		}
		return deleted;
		
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
		return deleteAndGet(toDelete) != null || mySize == 0;
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
		 * A local Iterator on bst trees.
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
