import java.util.Collection;

public class CollectionFacadeSet implements SimpleSet {
	Collection<String> myCollection;
	
	/**
	 * Creates a new facade wrapping the specified collection.
	 * @param collection The Collection to wrap.
	 */
	public CollectionFacadeSet(Collection<String> collection) {
		myCollection = collection;
	}
	
	@Override
	public boolean add(String newValue) {
		if (!myCollection.contains(newValue))
			return myCollection.add(newValue);
		return false;
	}

	@Override
	public boolean contains(String searchVal) {
		return myCollection.contains(searchVal);
	}

	@Override
	public boolean delete(String toDelete) {
		return myCollection.remove(toDelete);
	}

	@Override
	public int size() {
		return myCollection.size();
	}

}
