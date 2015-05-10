package oop.ex4.data_structures;

import java.util.*;

public class AvlTree extends BstTree implements Iterable<Integer> {
	private int mySize;
	private Node root;
	
	/**
	 * The default constructor.
	 */
	public AvlTree() {
		mySize = 0;
		root = null;
	}
	
	/**
	 * A constructor that builds the tree by adding the elements in the input array one by
	 * one. If a value appears more that once in the list, only the first appearance is added.
	 * 
	 * @param data the values to add to the tree.
	 */
	public AvlTree(int[] data) {
		this();
		if (data == null)
			return;
		
		for (int val : data) {
			add(val);
		}
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
		this();
		for (int val : avlTree) {
			add(val);
		}
	}
	
	/**
	 * Add a new node with the given key to the tree.
	 * 
	 * @param newValue the value of the new node to add.
	 * @return true if the value to add is not already in the tree nad it was successfully added,
	 * false otherwise.
	 */
	public boolean add(int newValue) {
		if (super.add(newValue)) {
			Node unbalancing = findUnbalancingNode(root);
			if (unbalancing == null) {
				return true;
			} else {
				fixAvl(unbalancing);
				return true;
			}
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
		return 0;
	}
	
	/**
	 * Removes the node with the given value from the tree, if it exists.
	 * 
	 * @param toDelete the value to remove from the tree.
	 * @return true if the give value was found and deleted, false otherwise.
	 */
	public boolean delete(int toDelete) {
		return false;
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
		Node current = min(root);
		if (current == null)
			return null;
		return new TreeIterator(this);
	}
	
	private void setHeights(Node avlRoot) {
		
	}
	
	private void fixAvl(Node unbalancing) {
		
	}
	
	private Node findUnbalancingNode(Node avlRoot) {
		return null;
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
	
}
