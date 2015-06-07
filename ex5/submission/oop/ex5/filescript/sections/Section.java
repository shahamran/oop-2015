package filescript.sections;
import filescript.TypeOneException;
import filescript.sections.filters.*;
import filescript.sections.orders.*;

/**
 * This class represents a section in the commands file.
 * Holds the Filter and Order objects, as well as a list of warnings (type i exceptions) that were thrown. 
 * @author ransha
 */
public class Section {
	private Filter myFilter;
	private Order myOrder;
	private TypeOneException[] warnings = {};
	
	/**
	 * Constructs a new Section with empty (null) filter and order objects.
	 */
	public Section() {
		myFilter = null;
		myOrder = null;
	}
	
	/**
	 * 
	 * Constructs a new Section with given filter and order objects
	 * @param newFilter The filter object to store.
	 * @param newOrder The order object to store.
	 */
	public Section(Filter newFilter, Order newOrder) {
		setFilter(newFilter);
		setOrder(newOrder);
	}

	/**
	 * @return The Order object this section stores.
	 */
	public Order getOrder() {
		return myOrder;
	}

	/**
	 * @param newOrder The Order Object this sections will store.
	 */
	public void setOrder(Order newOrder) {
		myOrder = newOrder;
	}
	
	/**
	 * @return The Filter object this section stores.
	 */
	public Filter getFilter() {
		return myFilter;
	}

	/**
	 * @param newFilter The Filter object this section will store.
	 */
	public void setFilter(Filter newFilter) {
		myFilter = newFilter;
	}
	
	/**
	 * @param warning The type i exception to add to the warnings list.
	 */
	public void addWarning(TypeOneException warning) {
		if (warning == null)
			return;
		int newLength = warnings.length + 1;
		TypeOneException[] newArray = new TypeOneException[newLength];
		for (int i = 0; i < warnings.length; i++) {
			newArray[i] = warnings[i];
		}
		newArray[newLength-1] = warning;
		warnings = newArray;
	}
	
	/**
	 * @return The Type I Exceptions list this section holds.
	 */
	public TypeOneException[] getWarnings() {
		return warnings;
	}
}
