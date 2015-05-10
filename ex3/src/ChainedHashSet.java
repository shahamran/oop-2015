import java.util.LinkedList;

public class ChainedHashSet extends SimpleHashSet {
	private Bucket[] hashTable;
	
	private void setEmptyTable() {
		mySize = 0;
		myCapacity = START_CAPACITY;
		hashTable = new Bucket[myCapacity];
	}
	
	/**
	 * A default constructor. Constructs a new, empty table with default initial capacity (16),
	 * upper load factor (0.75) and lower load factor (0.25).
	 */
	public ChainedHashSet() {
		setEmptyTable();
	}
	
	/**
	 * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
	 * @param upperLoadFactor The upper load factor of the hash table.
	 * @param lowerLoadFactor The lower load factor of the hash table.
	 */
	public ChainedHashSet(float upperLoadFactor, float lowerLoadFactor) {
		setEmptyTable();
		upperLoad = upperLoadFactor;
		lowerLoad = lowerLoadFactor;
	}
	
	/**
	 * Data constructor - builds the hash set by adding the elements one by one. 
	 * Duplicate values should be ignored. The new table has the default values of initial capacity (16), 
	 * upper load factor (0.75), and lower load factor (0.25).
	 * @param data Values to add to the set.
	 */
	public ChainedHashSet(String[] data) {
		setEmptyTable();
		for (String value : data) {
			if (value != null)
				add(value);
		}
	}
	
	/**
	 * Checks whether the current load factor is between the legal limits.
	 * If so, does nothing, Otherwise, re-hashes accordingly (builds a new sized array).
	 */
	private void reHash() {
		// Reset the table's size.
		mySize = 0;
		Bucket[] oldTable = hashTable;
		hashTable = new Bucket[myCapacity];
		LinkedList<String> bucket;
		// Insert all values from the old array to the new one.
		for (int i=0 ; i < oldTable.length; i++) {
			if (oldTable[i] == null)
				continue;
			bucket = oldTable[i].getList();
			for (String value : bucket)
				add(value);
		}
	}

	
	/* (non-Javadoc)
	 * @see SimpleHashSet#add(java.lang.String)
	 */
	@Override
	public boolean add(String newValue) {
		if (newValue == null)
			return false;
		
		int index = hashFunction(newValue,0);
		if (hashTable[index] == null) 
			hashTable[index] = new Bucket();
		
		if (hashTable[index].contains(newValue))
			return false;
		hashTable[index].add(newValue);
		mySize++;
		if (getLoadFactor() > upperLoad) {
			increaseCapacity();
			reHash();
		}
			
		return true;
	}

	/* (non-Javadoc)
	 * @see SimpleHashSet#contains(java.lang.String)
	 */
	@Override
	public boolean contains(String searchVal) {
		if (searchVal == null)
			return false;
		
		int index = hashFunction(searchVal,0);
		if (hashTable[index] != null) {
			if (hashTable[index].contains(searchVal))
				return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see SimpleHashSet#delete(java.lang.String)
	 */
	@Override
	public boolean delete(String toDelete) {
		if (toDelete == null)
			return false;
		boolean couldDelete = false;
		int index = hashFunction(toDelete,0);
		if (hashTable[index] != null) {
			couldDelete = hashTable[index].delete(toDelete);
		}
		if (couldDelete) {
			mySize--;
			if (getLoadFactor() < lowerLoad) {
				decreaseCapacity();
				reHash();
			}
				
			return true;
		} else {
			return false;
		}
	}

}
