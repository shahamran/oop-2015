
public abstract class SimpleHashSet implements SimpleSet {
	protected int mySize = 0, myCapacity = 16;
	protected float upperLoad = 0.75f, lowerLoad = 0.25f;
	protected static final int START_CAPACITY = 16;
	// see explanation about EMPTY_SPOT in README - NOTES - (1).
	protected static final String EMPTY_SPOT = new String("");
	// This data member keeps size of the table minus one for the bitwise modulu thingy.
	protected int lastIndex = myCapacity - 1;
	
	@Override
	public abstract boolean add(String newValue);

	@Override
	public abstract boolean contains(String searchVal);

	@Override
	public abstract boolean delete(String toDelete);
	
	/**
	 * 
	 * @param value The string that should be indexed.
	 * @param i the The attempt number
	 * @return The value assigned to the object fitted to the appropriate range.
	 */
	protected int hashFunction(String value, int i) {
		return (value.hashCode() + (i + i * i) / 2) & lastIndex;
	}
	
	protected int hashFunction(String value) {
		return value.hashCode() & lastIndex;
	}
	
	protected float getLoadFactor() {
		return ((float) mySize / myCapacity);
	}
	
	protected void increaseCapacity() {
		myCapacity <<= 1;
		lastIndex = myCapacity - 1;
	}
	
	protected void decreaseCapacity() {
		myCapacity >>= 1;
		lastIndex = myCapacity - 1;
	}
	
	@Override
	public int size() {
		return mySize;
	}
	
	public int capacity() {
		return myCapacity;
	}

}
