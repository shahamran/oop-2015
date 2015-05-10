/**
 * An implementation of a hash table using quadratic probing to find empty spots in the table.
 * @author ransha
 *
 */
public class OpenHashSet extends SimpleHashSet {
	private String[] hashTable;
	
	/**
	 * Sets all paramters to default. Used by constructor('s).
	 */
	private void setEmptyTable() {
		mySize = 0;
		myCapacity = START_CAPACITY;
		hashTable = new String[myCapacity];
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
	 * Creates a new hash table and adds all of the old values to it.
	 */
	private void reHash() {
		mySize = 0;
		String[] oldTable = hashTable;
		String value;
		hashTable = new String[myCapacity];
		// Insert all values from the old array to the new one.
		for (int i = 0; i < oldTable.length; i++) {
			value = oldTable[i];
			if (value != null && value != EMPTY_SPOT) {
				addUnique(value);
			}
		}
	}
	
	/**
	 * Searches a string in the hash-table. Returns its index if found or -1 if not found.
	 * 
	 * @param searchVal The string to search
	 * @return The string's index in the hash table if found, -1 otherwise.
	 */
	private int findIndexOf(String searchVal) {
		int attempt = 0, firstIndex = hashFunction(searchVal), index = firstIndex;
		String currentString = hashTable[firstIndex];
		boolean foundValue = false, checkedAll = false;
		
		while (!foundValue && !checkedAll) {
			currentString = hashTable[index];
			if (currentString == null) {
				return -1;
			} else {
				if (currentString != EMPTY_SPOT) {
					foundValue = currentString.equals(searchVal);
					if (foundValue)
						break;
				}
			}
			attempt++;
			index = hashFunction(searchVal,attempt);
			checkedAll = index == firstIndex;
		}
		// Returns the index if found or -1 if not.
		return foundValue ? index : -1;
	}
	
	
	private void addUnique(String newVal) {
		int attempt = 0;
		int index = (newVal.hashCode() & lastIndex);
		String currentSpot = hashTable[index];
		while (currentSpot != null && currentSpot != EMPTY_SPOT) {
			attempt++;
			index = hashFunction(newVal,attempt);
			currentSpot = hashTable[index];
		}
		hashTable[index] = newVal;
		mySize++;
	}
	
	@Override
	/**
	 * Adds a value to the table and increases the tableSize member. Then checks if the load factor is
	 * greater than the accepted limit, and reHashes if needed.
	 */
	public boolean add(String newValue) {
		if (newValue == null)
			return false; // Check for invalid inputs.
		
		
		// Only add if the table doesn't already contain this value.
		if (contains(newValue)) {
			return false;
		} else {
			// Keep probing until an empty spot is found.
			addUnique(newValue);
			if (getLoadFactor() > upperLoad) {
				increaseCapacity();
				reHash();
			}
			return true;
		}
	}

	
	@Override
	/**
	 * Uses the findIndexOf() method to determine wheter an item is in the table or not.
	 */
	public boolean contains(String searchVal) {
		int index = findIndexOf(searchVal);
		if (index == -1) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	/**
	 * Uses findIndexOf() to find the string to delete and put a DELETED mark instead of it.
	 * Then checks if a re hash is needed and does so if so.
	 */
	public boolean delete(String toDelete) {
		if (toDelete == null)
			return false; // Check for invalid input.
		int index = findIndexOf(toDelete);
		if (index == -1) { // Meaning : if found.
			return false;
		} else {
			hashTable[index] = EMPTY_SPOT;
			mySize--;
			if (getLoadFactor() < lowerLoad && myCapacity > 1) {
				decreaseCapacity();
				reHash();
			}
			return true;
		}
	}

}
