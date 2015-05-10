package oop.ex4.data_structures;

import java.util.Iterator;

public class TreeIterator implements Iterator<Integer> {
	private BstTree myTree;
	private Node current;
	
	public TreeIterator(BstTree bstTree) {
		myTree = bstTree;
		current = myTree.root;
	}
	
	@Override
	public boolean hasNext() {
		if (current == null)
			return false;
		if (myTree.successor(current) == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Integer next() {
		if (current == null)
			return null;
		if (hasNext()) {
			current = myTree.successor(current);
			return current.getKey();
		} else {
			return null;
		}
	}

	@Override
	public void remove() {
		
	}
	
}
