package filescript.sections.orders;

import filescript.TypeOneException;

/**
 * A general non-critical exception in the orders sub-section.
 * @author ransha
 */
public abstract class OrderException extends TypeOneException {
	private static final long serialVersionUID = 1L;

	public OrderException(String msg) {
		super(msg);
	}

}
