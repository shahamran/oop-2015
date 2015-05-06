import java.util.LinkedList;
/**
 * This object holds a linked list of strings and allows handling basic linked list methods.
 * @author ransha
 *
 */
public class Bucket {
	private LinkedList<String> myList;

	/**
	 * Simple constructor for the Bucket object.
	 */
	public Bucket() {
		myList = new LinkedList<String>();
	}
	
	/**
	 * Attempts to add a new value to the bucket.
	 * @param newValue The string value to add.
	 * @return True if the string value was added, false otherwise.
	 */
	public boolean add(String newValue) {
		if (!myList.contains(newValue)) {
			myList.add(newValue);
			return true;
		}
		return false;
	}
	
	/**
	 * @param searchValue The value to search in the bucket
	 * @return True if the item is in the bucket, false otherwise.
	 */
	public boolean contains(String searchValue) {
		return myList.contains(searchValue);
	}

	/**
	 * Attempts to delete a value from the bucket.
	 * @param toDelete The string value to delete.
	 * @return True if the string value was deleted, false otherwise.
	 */
	public boolean delete(String toDelete) {
		return myList.remove(toDelete);
	}
	
	/**
	 * @return The list object that the bucket holds.
	 */
	public LinkedList<String> getList() {
		return myList;
	}

}
