package filescript.sections.orders;

import java.io.File;

/**
 * A decorator class for reverse orders. Gets an Order object and returns the opposite of what it
 * would have returned (in the compare() method);
 * @author ransha
 */
public class ReverseOrder extends Order {
	private Order myOrder;
	private static final int REVERSE = -1;
	
	/**
	 * @param newOrder The order to decorate.
	 */
	public ReverseOrder(Order newOrder) {
		myOrder = newOrder;
	}
	
	@Override
	public int compare(File o1, File o2) {
		return REVERSE * (myOrder.compare(o1,  o1));
	}
}
