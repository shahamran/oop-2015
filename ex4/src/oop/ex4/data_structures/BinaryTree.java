package oop.ex4.data_structures;

public interface BinaryTree {
	public Node getRoot();
	public boolean add(int newValue);
	public boolean delete(int toDelete);
	public int contains(int searchVal);
	public int size();
}
