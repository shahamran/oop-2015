package oop.ex4.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

public class BstIterator implements Iterator<Integer> {
	
	private Node current;
	
	public BstIterator(Node root) {
		if (root == null) {
			current = null;
		} else {
			// The first node when iterating (in-order) is the minimum value of this tree.
			current = BstTree.getMin(root);
		}
	}
	
	@Override
	public boolean hasNext() {
		return current != null;
	}

	@Override
	public Integer next() throws NoSuchElementException {
		if (!hasNext())
			throw new NoSuchElementException();
		int val = current.getKey();
		// Switch to the next node in the tree.
		current = BstTree.successor(current);
		return val;
	}

	@Override
	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
		
	}

	
}
