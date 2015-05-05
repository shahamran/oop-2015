
public abstract class SimpleHashSet implements SimpleSet {
	protected int mySize = 0, loadFactor = 0, myCapacity = 0;
	
	@Override
	public abstract boolean add(String newValue);

	@Override
	public abstract boolean contains(String searchVal);

	@Override
	public abstract boolean delete(String toDelete);

	@Override
	public int size() {
		return mySize;
	}
	
	public int capacity() {
		return myCapacity;
	}

}
