package filescript.sections.orders;

import java.io.File;

public class ReverseOrder extends Order {
	Order myOrder;
	private static final int REVERSE = -1;
	
	public ReverseOrder(Order newOrder) {
		myOrder = newOrder;
	}
	
	@Override
	public int compare(File o1, File o2) {
		return (REVERSE * (myOrder.compare(o1,  o1)));
	}
}
