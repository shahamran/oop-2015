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
	 * 
	 * @param newKey
	 */
	public Node(int newKey) {
		key = newKey;
		left = null; right = null; parent = null;
	}
	
	/**
	 * 
	 * @param newKey
	 * @param newParent
	 */
	public Node(int newKey, Node newParent) {
		parent = newParent;
		key = newKey;
	}
	
	/**
	 * 
	 * @param newKey
	 * @param newParent
	 * @param newLeft
	 * @param newRight
	 */
	public Node(int newKey, Node newParent, Node newLeft, Node newRight) {
		this(newKey, newParent);
		left = newLeft;
		right = newRight;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public Node getRight() {
		return right;
	}
	
	public boolean setRight(Node newRight) {
		if (newRight == null) {
			right = null;
			return true;
		}
		if (newRight.getKey() > key) {
			right = newRight;
			return true;
		} else {
			return false;
		}
	}
	
	public Node getLeft() {
		return left;
	}
	
	public boolean setLeft(Node newLeft) {
		if (newLeft == null) {
			left = null;
			return true;
		}
		if (newLeft.getKey() < key) {
			left = newLeft;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean setChild(Node newChild) {
		if (newChild == null) {
			return false;
		}
		if (newChild.getKey() > this.key) {
			return setRight(newChild);
		} else if (newChild.getKey() < this.key) {
			 return setLeft(newChild);
		} else {
			return false;
		}
	}
	
	public void setParent(Node newParent) {
		parent = newParent;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getKey() {
		return key;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isRoot() {
		return (parent == null);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * 
	 * @param newHeight
	 */
	public void setHeight(int newHeight) {
		height = newHeight;
	}
}
