package oop.ex4.data_structures;

public class Node {
	private Node left,right,parent;
	private int key, height;
	
	public Node(int newKey) {
		key = newKey;
		left = null; right = null; parent = null;
	}
	
	public Node(int newKey, Node newParent) {
		parent = newParent;
		key = newKey;
	}
	
	public Node(int newKey, Node newParent, Node newLeft, Node newRight) {
		this(newKey, newParent);
		left = newLeft;
		right = newRight;
	}
	
	public Node getLeft() {
		return this.left;
	}
	
	public boolean setLeft(Node newLeft) {
		if (newLeft.getKey() < this.key){
			left = newLeft;
			return true;
		}
		return false;
	}
	
	public Node getRight() {
		return this.right;
	}
	
	public boolean setRight(Node newRight) {
		if (newRight.getKey() > this.key){
			right = newRight;
			return true;
		}
		return false;
	}
	
	public int getKey() {
		return key;
	}
	
	public boolean isRoot() {
		return (parent == null);
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void setHeight(int newHeight) {
		height = newHeight;
	}
}
