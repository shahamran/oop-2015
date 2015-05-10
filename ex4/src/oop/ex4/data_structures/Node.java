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
	
	/**
	 * 
	 * @return
	 */
	public Node getLeft() {
		return this.left;
	}
	
	/**
	 * 
	 * @param newLeft
	 * @return
	 */
	public boolean setLeft(Node newLeft) {
		if (newLeft.getKey() < this.key){
			left = newLeft;
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public Node getRight() {
		return this.right;
	}
	
	/**
	 * 
	 * @param newRight
	 * @return
	 */
	public boolean setRight(Node newRight) {
		if (newRight.getKey() > this.key){
			right = newRight;
			return true;
		}
		return false;
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
