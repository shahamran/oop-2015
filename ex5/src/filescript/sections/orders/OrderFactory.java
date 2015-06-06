package filescript.sections.orders;

public class OrderFactory {
	private static final String ABS = "abs", TYPE = "type", SIZE = "size", REVERSE = "REVERSE";
	private static final int NAME = 0;
	
	public static Order createOrder(String values[]) throws OrderException {
		if (values.length == 0)
			return null;
		
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
	
	private static boolean isReverseOrder(String[] vals) {
		boolean shouldReverse = false;
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
