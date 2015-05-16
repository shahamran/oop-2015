package oop.ex4.data_structures;

import java.util.*;

public class AvlTree extends BstTree {
	private static final int LEAF_HEIGHT = 0, NOT_FOUND = -1, NOT_AVL = -2;
	
	/**
	 * The default constructor.
	 */
	public AvlTree() {
		super();
	}
	
	/**
	 * A constructor that builds the tree by adding the elements in the input array one by
	 * one. If a value appears more that once in the list, only the first appearance is added.
	 * 
	 * @param data the values to add to the tree.
	 */
	public AvlTree(int[] data) {
		super(data);
	}
	
	/**
	 * A copy constructor that creates a deep copy of the give AvlTree. This means that for
	 * every node or any other internal object of the given tree, a new, identical object, is
	 * instantiated for the new tree (the internal object is not simply referenced from it). The
	 * new tree must contain all the values of the given tree, but not necessarily in the same
	 * structure.
	 * 
	 * @param avlTree an AVL tree.
	 */
	public AvlTree(AvlTree avlTree) {
		super(avlTree);
	}
	
	@Override
	/**
	 * Add a new node with the given key to the tree.
	 * 
	 * @param newValue the value of the new node to add.
	 * @return true if the value to add is not already in the tree and it was successfully added,
	 * false otherwise.
	 */
	public boolean add(int newValue) {
		if (super.add(newValue)) {
			setHeights(myRoot);
			Node unbalancing = findUnbalancingNode(myRoot);
			if (unbalancing != null) 
				fixAvl(unbalancing);
			return true;
		} else {
			return false;
		}
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
	 * Removes the node with the given value from the tree, if it exists.
	 * 
	 * @param toDelete the value to remove from the tree.
	 * @return true if the give value was found and deleted, false otherwise.
	 */
	public boolean delete(int toDelete) {
		if (super.delete(toDelete)) {
			setHeights(myRoot);
			Node unbalancing = findUnbalancingNode(myRoot);
			if (unbalancing != null) {
				fixAvl(unbalancing);
			}
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @return the number of nodes in the tree.
	 */
	public int size() {
		return mySize;
	}
	
	/**
	 * @return an iterator on the Avl Tree. The returned iterator iterates over the tree nodes
	 * in an ascending order, and does NOT implement the remove() method.
	 */
	public Iterator<Integer> iterator() {
		return super.iterator();
	}
	
	private void fixAvl(Node unbalancing) {
		int left = unbalancing.getLeft().getHeight(),
			right = unbalancing.getRight().getHeight();
		
		if (left > right) {
			left = unbalancing.getLeft().getLeft().getHeight();
			right = unbalancing.getLeft().getRight().getHeight();
			if (left > right) {
				rotateLL(unbalancing);
			} else {
				rotateLR(unbalancing);
			}
		} else {
			left = unbalancing.getRight().getLeft().getHeight();
			right = unbalancing.getRight().getRight().getHeight();
			if (left > right) {
				rotateRL(unbalancing);
			} else {
				rotateRR(unbalancing);
			}
		}
		setHeights(unbalancing);
	}
	
	private void rotateLL(Node x) {
		Node tempRight = x.getLeft().getRight();
		x.getLeft().setParent(x.getParent());
		x.setParent(x.getLeft());
		x.getLeft().setRight(x);
		x.setLeft(tempRight);
	}
	
	private void rotateLR(Node x) {
		Node tempLR = x.getLeft().getRight();
		tempLR.setParent(x);
		x.getLeft().setRight(tempLR.getLeft());
		tempLR.setLeft(x.getLeft());
		x.getLeft().setParent(tempLR);
		x.setLeft(tempLR);
		rotateLL(x);	
	}

	private void rotateRL(Node x) {
		Node tempRL = x.getRight().getLeft();
		tempRL.setParent(x);
		x.getRight().setLeft(tempRL.getRight());
		tempRL.setRight(x.getRight());
		x.getRight().setParent(tempRL);
		x.setRight(tempRL);
		rotateRR(x);
	}
	
	private void rotateRR(Node x) {
		Node tempLeft = x.getRight().getLeft();
		x.getRight().setParent(x.getParent());
		x.setParent(x.getRight());
		x.getRight().setLeft(x);
		x.setRight(tempLeft);
	}
	
	private Node findUnbalancingNode(Node x) {
		if (x == null) 
			return null;
		int left,right;
		left = x.getLeft() != null ? x.getLeft().getHeight() : -1;
		right = x.getRight() != null ? x.getRight().getHeight() : -1;
		if (Math.abs(left - right) > 1) {
			return x;
		}
		Node leftUnbalancing = findUnbalancingNode(x.getLeft());
		if (leftUnbalancing != null) {
			return leftUnbalancing;
		} else {
			return findUnbalancingNode(x.getRight());
		}
	}
	
	/**
	 * Calculates the minimum number of nodes in an AVL tree of the height h.
	 * 
	 * @param h the height of the tree (a non-negative number) in question.
	 * @return the minimum number of nodes in an AVL tree of the given height.
	 */
	public static int findMinNodes(int h) {
		return 0;
	}
	
	
	private int isAvl(Node x) {
		if (x == null) {
			return LEAF_HEIGHT - 1;
		}
		int left = isAvl(x.getLeft());
		if (left == NOT_AVL) {
			return NOT_AVL;
		}
		int right = isAvl(x.getRight());
		if (right == NOT_AVL) {
			return NOT_AVL;
		}
		if (Math.abs(left - right) > 1) {
			return NOT_AVL;
		} else {
			x.setHeight(Math.max(left, right) + 1);
			return x.getHeight();
		}
	}
	
	private int setHeights(Node x) {
		if (x == null) {
			return LEAF_HEIGHT - 1;
		}
		int left = setHeights(x.getLeft()), right = setHeights(x.getRight());
		x.setHeight(Math.max(left, right) + 1);
		return x.getHeight();
	}
	
}
