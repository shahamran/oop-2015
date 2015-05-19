package oop.ex4.data_structures;

/**
 * 
 * @author ransha
 *
 */
public class Node {
	private Node left,right,parent;
	private int key, height;
	
	/**
	 * Constructs a new Node object with a given key.
	 * @param newKey The key for this node.
	 */
	public Node(int newKey) {
		key = newKey;
		left = null; right = null; parent = null;
	}
	
	/**
	 * @return The parent object for this node.
	 */
	public Node getParent() {
		return parent;
	}
	
	/**
	 * @return The right child of this node.
	 */
	public Node getRight() {
		return right;
	}
	
	/**
	 * Sets the right child of this node to be a give node (or null).
	 * Also sets the parent of the new child to be this node.
	 * @param newRight The desired new right child for this node.
	 * @return False if the new node can't be the right child of this node, True otherwise.
	 */
	public boolean setRight(Node newRight) {
		if (newRight == null) {
			right = null;
			return true;
		}
		if (newRight.getKey() > this.key) {
			right = newRight;
			newRight.setParent(this);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @return The left child of this node.
	 */
	public Node getLeft() {
		return left;
	}
	
	/**
	 * Sets the left child of this node to be a give node (or null).
	 * Also sets the parent of the new child to be this node.
	 * @param newLeft The desired new leftt child for this node.
	 * @return False if the new node can't be the left child of this node, True otherwise.
	 */
	public boolean setLeft(Node newLeft) {
		if (newLeft == null) {
			left = null;
			return true;
		}
		if (newLeft.getKey() < this.key) {
			left = newLeft;
			newLeft.setParent(this);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Sets a new node (leaf) as a (correct) child of this one (if its key is different).
	 * @param newValue The value of the node to add.
	 * @return True if the new value was added successfully, false otherwise.
	 */
	public boolean setChild(int newValue) {
		if (newValue == this.key) {
			return false;
		} else {
			if (newValue > this.key) {
				return this.setRight(new Node(newValue));
			} else {
				return this.setLeft(new Node(newValue));
			}
		}
	}
	
	/**
	 * Sets the parent of this node to be the given node.
	 * @param newParent The new parent for this node.
	 */
	public void setParent(Node newParent) {
		parent = newParent;
	}
	
	/**
	 * @return The key this node holds.
	 */
	public int getKey() {
		return key;
	}
	
	/**
	 * @return The height of this node.
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * @param newHeight Set the height of this node to be this value.
	 */
	public void setHeight(int newHeight) {
		height = newHeight;
	}
	
}
