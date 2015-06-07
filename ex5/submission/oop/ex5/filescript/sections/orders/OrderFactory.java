package filescript.sections.orders;

/**
 * A class that creates an Order object according to given string arguments.
 * @author ransha
 */
public class OrderFactory {
	private static final String ABS = "abs", TYPE = "type", SIZE = "size", REVERSE = "REVERSE";
	private static final int NAME = 0, EXPECTED_ARGS = 1;
	
	public static Order createOrder(String values[]) throws OrderException {
		if (values.length != EXPECTED_ARGS || values.length != EXPECTED_ARGS + 1)
			throw new BadOrderValuesException();
		
		Order outOrder = null;
		String orderName = values[NAME];
		switch (orderName) {
		case ABS:
			outOrder = new AbsOrder();
			break;
		case TYPE:
			outOrder = new TypeOrder();
			break;
		case SIZE:
			outOrder = new SizeOrder();
			break;
			default:
				throw new BadOrderNameException(orderName);
		}
		
		if (isReverseOrder(values)) {
			outOrder = new ReverseOrder(outOrder);
		}
		
		return outOrder;
	}
	
	/**
	 * @param vals The string arguments that were given.
	 * @return True if you should reverse the order, false otherwise.
	 */
	private static boolean isReverseOrder(String[] vals) {
		boolean shouldReverse = false;
		// Basiclly checks if and odd number of #REVERSE arguments was given.
		for (int i = vals.length - 1; i >= 0; i--) {
			if (vals[i].equals(REVERSE)) {
				shouldReverse = !shouldReverse;
			} else {
				break;
			}
		}
		return shouldReverse;
	}
}
