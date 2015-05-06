
public class OpenHashSet extends SimpleHashSet {
	private String[] hashTable;
	
	private void setEmptyTable() {
		mySize = 0;
		myCapacity = START_CAPACITY;
		hashTable = new String[myCapacity];
		loadFactor = 0;
	}
	
	/**
	 * A default constructor. Constructs a new, empty table with default initial capacity (16),
	 * upper load factor (0.75) and lower load factor (0.25).
	 */
	public OpenHashSet() {
		setEmptyTable();
	}
	
	/**
	 * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
	 * @param upperLoadFactor The upper load factor of the hash table.
	 * @param lowerLoadFactor The lower load factor of the hash table.
	 */
	public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
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
	public OpenHashSet(String[] data) {
		setEmptyTable();
		if (data.length == 0)
			return;
		for (int i=0; i < data.length; i++) {
			if (data[i] != null)
				add(data[i]);
		}
	}
	
	/**
	 * Checks whether the current load factor is between the legal limits.
	 * If so, does nothing, Otherwise, re-hashes accordingly (builds a new sized array).
	 */
	private void reHash() {
		if (loadFactor >= upperLoad) {
			increaseCapacity();
		} else if (loadFactor <= lowerLoad) {
			decreaseCapacity();
		} else {
			return;
		}
	
		String[] oldTable = hashTable;
		String value;
		hashTable = new String[myCapacity];
		mySize = 0;
		// Insert all values from the old array to the new one.
		for (int i=0 ; i < oldTable.length; i++) {
			value = oldTable[i];
			if (value == EMPTY_SPOT || value == null) 
				continue;
			
			add(value);
		}
	}
	
	/**
	 * Searches a string in the hash-table. Returns its index if found or -1 if not found.
	 * 
	 * @param searchVal The string to search
	 * @return The string's index in the hash table if found, -1 otherwise.
	 */
	private int findIndexOf(String searchVal) {
		int attempt = 0;
		int firstIndex = hashFunction(searchVal, attempt);
		String currentString;
		int index = -1;
		boolean foundValue = false, checkedAll = false;
		
		while (!(foundValue || checkedAll)) {
			index = hashFunction(searchVal,attempt);
			currentString = hashTable[index];
			if (currentString != null && currentString != EMPTY_SPOT) {
				foundValue = currentString.equals(searchVal);
			}
			checkedAll = index == firstIndex;
			attempt++;
		}
		return foundValue ? index : -1;
	}
	
	/* (non-Javadoc)
	 * @see SimpleHashSet#add(java.lang.String)
	 */
	@Override
	public boolean add(String newValue) {
		if (newValue == null)
			return false;
		
		int attempt = 1;
		int index = hashFunction(newValue,0);
		String currentSpot = hashTable[index];
		
		if (!contains(newValue)) {
			while (!(currentSpot == null || currentSpot == EMPTY_SPOT)) {
				index = hashFunction(newValue,attempt);
				currentSpot = hashTable[index];
				attempt++;
			}
			hashTable[index] = newValue;
			increaseSize();
			if (loadFactor >= upperLoad) {
				reHash();
			}
			return true;
		}
		
		return false;
	}

	
	/* (non-Javadoc)
	 * @see SimpleHashSet#contains(java.lang.String)
	 */
	@Override
	public boolean contains(String searchVal) {
		int index = findIndexOf(searchVal);
		if (index == -1) {
			return false;
		} else {
			return true;
		}
	}

	/* (non-Javadoc)
	 * @see SimpleHashSet#delete(java.lang.String)
	 */
	@Override
	public boolean delete(String toDelete) {
		if (toDelete == null)
			return false;
		int index = findIndexOf(toDelete);
		if (index != -1) {
			hashTable[index] = EMPTY_SPOT;
			decreaseSize();
			if (loadFactor <= lowerLoad && myCapacity > 1) 
				reHash();
			return true;
		} else {
			return false;
		}
	}

}
