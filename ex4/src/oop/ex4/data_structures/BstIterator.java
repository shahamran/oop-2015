package oop.ex4.data_structures;

import java.util.Iterator;

public class BstIterator implements Iterator<Integer> {
	private Node current;
	
	public BstIterator(Node root) {
		if (root == null) {
			current = null;
		} else {
			current = BstTree.getMin(root);
		}
	}
	
	@Override
	public boolean hasNext() {
		return current != null;
	}

	@Override
	public Integer next() {
		int val = current.getKey();
		current = BstTree.successor(current);
		return val;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	
}
