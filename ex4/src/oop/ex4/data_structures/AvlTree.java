package oop.ex4.data_structures;

import java.util.*;

public class AvlTree extends BstTree {
	private static final int LEAF_HEIGHT = 0;
	
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
			Node addedNode = getNodeWithVal(newValue);
			Node unbalancing = findUnbalancingNode(addedNode);
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
		return super.contains(searchVal);
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
		return super.size();
	}
	
	/**
	 * @return an iterator on the Avl Tree. The returned iterator iterates over the tree nodes
	 * in an ascending order, and does NOT implement the remove() method.
	 */
	public Iterator<Integer> iterator() {
		return super.iterator();
	}
	
	private void fixAvl(Node unbalancing) {
		int left,right;
		left = getHeightOf(unbalancing.getLeft());
		right = getHeightOf(unbalancing.getRight());
		
		if (left > right) {
			left = getHeightOf(unbalancing.getLeft().getLeft());
			right = getHeightOf(unbalancing.getLeft().getRight());
			if (left > right) {
				rotateLL(unbalancing);
			} else {
				rotateLR(unbalancing);
			}
		} else {
			left = getHeightOf(unbalancing.getRight().getLeft());
			right = getHeightOf(unbalancing.getRight().getRight());
			if (left > right) {
				rotateRL(unbalancing);
			} else {
				rotateRR(unbalancing);
			}
		}
		setHeights(myRoot);
	}
	
	private void rotateLL(Node x) {
		Node tempParent = x.getParent(), left = x.getLeft();
		x.setLeft(left.getRight());
		left.setRight(x);
		if (tempParent != null) {
			if (tempParent.getKey() > left.getKey()) {
				tempParent.setLeft(left);
			} else {
				tempParent.setRight(left);
			}
		} else {
			myRoot = left;
			left.setParent(null);
		}
	}
	
	private void rotateLR(Node x) {
		Node left = x.getLeft(), tempLR = left.getRight();
		left.setRight(tempLR.getLeft());
		tempLR.setLeft(left);
		x.setLeft(tempLR);
		rotateLL(x);
	}

	private void rotateRL(Node x) {
		Node right = x.getRight(), tempRL = right.getLeft();
		right.setLeft(tempRL.getRight());
		tempRL.setRight(right);
		x.setRight(tempRL);
		rotateRR(x);
	}
	
	private void rotateRR(Node x) {
		Node tempParent = x.getParent(), right = x.getRight();
		x.setRight(right.getLeft());
		right.setLeft(x);
		if (tempParent != null) {
			if (tempParent.getKey() > right.getKey()) {
				tempParent.setLeft(right);
			} else {
				tempParent.setRight(right);
			}
		} else {
			myRoot = right;
			right.setParent(null);
		}
	}
	
	private Node findUnbalancingNode(Node x) {
		if (x == null) 
			return null;
		int left,right;
		left = getHeightOf(x.getLeft());
		right = getHeightOf(x.getRight());
		if (Math.abs(left - right) > 1) {
			return x;
		}
		return (findUnbalancingNode(x.getParent()));
	}
	
	/**
	 * Calculates the minimum number of nodes in an AVL tree of the height h.
	 * 
	 * @param h the height of the tree (a non-negative number) in question.
	 * @return the minimum number of nodes in an AVL tree of the given height.
	 */
	public static int findMinNodes(int h) {
		if (h < 0) {
			return 0;
		} else {
			return findMinNodes(h-1) + findMinNodes(h-2) + 1;
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
	
	private int getHeightOf(Node x) {
		if (x == null) 
			return LEAF_HEIGHT - 1;
		return x.getHeight();	
	}
	
}
