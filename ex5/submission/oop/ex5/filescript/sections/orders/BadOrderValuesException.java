package filescript.sections.orders;

/**
 * Thrown when illegal order line was read.
 * @author ransha
 */
public class BadOrderValuesException extends OrderException {
	private static final long serialVersionUID = 1L;

	public BadOrderValuesException() {
		super("Bad order arguments given.");
	}
	
	public BadOrderValuesException(String msg) {
		super(msg);
	}

}
