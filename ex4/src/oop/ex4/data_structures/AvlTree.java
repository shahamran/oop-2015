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
	 * A copy constructor that creates a deep copy of the given AvlTree. This means that for
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
		if (super.add(newValue)) { // Check if the node was successfully added as a regular bst node.
			setHeights(myRoot); // Update nodes' heights.
			Node addedNode = getNodeWithVal(newValue); // Search the tree for the added node.
			Node unbalancing = findUnbalancingNode(addedNode); // Check the AVL property.
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
		if (contains(toDelete) == NOT_FOUND)
			return false;
		Node deleted = super.deleteAndGet(toDelete);
		if (deleted != null) {
			setHeights(myRoot);
			Node unbalancing = findUnbalancingNode(deleted);
			/* When deleting a node, we can have multiple AVL property breaks. We've seen in DAST that all
			 * of them will be in the route between the deleted node and the root - so we check for that. */
			while (unbalancing != null) {
				fixAvl(unbalancing);
				unbalancing = findUnbalancingNode(unbalancing);
			}
			return true;
		} else {
			/* This part determines whether we got null from the delete
			 * method because it was deleted, or because the deleted node
			 * didn't have a parent - meaning it is the root node. */
			if (mySize == 0) {
				return true;
			} else {
				return false;
			}
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
	
	/*
	 * Fixes the AVL property by rotating around the unbalancing Node according to the tree state.
	 */
	private void fixAvl(Node unbalancing) {
		if (unbalancing == null)
			return;
		
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
	
	/*
	 * Fix AVL LL situation
	 */
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
	
	/*
	 * Fix AVL LR situation
	 */
	private void rotateLR(Node x) {
		Node left = x.getLeft(), tempLR = left.getRight();
		left.setRight(tempLR.getLeft());
		tempLR.setLeft(left);
		x.setLeft(tempLR);
		rotateLL(x);
	}
	
	/*
	 * Fix AVL RL situation
	 */
	private void rotateRL(Node x) {
		Node right = x.getRight(), tempRL = right.getLeft();
		right.setLeft(tempRL.getRight());
		tempRL.setRight(right);
		x.setRight(tempRL);
		rotateRR(x);
	}
	
	/*
	 * Fix AVL RR situation
	 */
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
	
	/*
	 * Checks all the nodes from a given node to the root if the avl property is broken. 
	 */
	private Node findUnbalancingNode(Node x) {
		if (x == null) 
			return null;
		int left,right;
		left = getHeightOf(x.getLeft());
		right = getHeightOf(x.getRight());
		if (Math.abs(left - right) > 1) {
			return x;
		}
		// Recursively check every node up to the root.
		return (findUnbalancingNode(x.getParent()));
	}
	
	/*
	 * Sets the heights of the subtree x's nodes recursively.
	 */
	private int setHeights(Node x) {
		if (x == null) {
			return LEAF_HEIGHT - 1;
		}
		int left = setHeights(x.getLeft()), right = setHeights(x.getRight());
		x.setHeight(Math.max(left, right) + 1);
		return x.getHeight();
	}
	
	/*
	 * @return The height of a node if it exists, -1 otherwise.
	 */
	private int getHeightOf(Node x) {
		if (x == null) 
			return LEAF_HEIGHT - 1;
		return x.getHeight();	
	}
	
}
