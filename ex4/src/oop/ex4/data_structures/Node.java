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
			newRight.setParent(this);
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
			newLeft.setParent(this);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean setChild(int newVal) {
		Node newNode = new Node(newVal);;
		if (newVal > this.key) { 
			return setRight(newNode);
		} else if (newVal < this.key) {
			 return setLeft(newNode);
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
