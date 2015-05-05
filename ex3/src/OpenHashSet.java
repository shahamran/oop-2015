
public class OpenHashSet extends SimpleHashSet {
	private int capacity;
	private String[] hashTable;
	
	private int hashFunction(String value, int i) {
		return value.hashCode() + (i + i ^ 2) / 2;
	}
	
	public OpenHashSet() {
		hashTable = new String[1];
	}
	/* (non-Javadoc)
	 * @see SimpleHashSet#add(java.lang.String)
	 */
	@Override
	public boolean add(String newValue) {
		if (!contains(newValue)) {
			int index = probe(newValue);
			hashTable[index] = newValue;
			return true;
		}
		return false;
	}

	private int probe(String newValue) {
		int index = newValue.hashCode(), i = 0;
		while (hashTable[index] != null) {
			i++;
			index = hashFunction(newValue,i) & (capacity - 1);
		}
		return index;
	}
	
	private int findIndexOf(String searchVal) {
		return -1;
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
		int index = findIndexOf(toDelete);
		if (index != -1) {
			hashTable[index] = null;
		}
		return false;
	}

}
