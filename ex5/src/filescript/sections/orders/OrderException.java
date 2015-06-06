package filescript.sections.orders;

import filescript.TypeOneException;

public abstract class OrderException extends TypeOneException {
	private static final long serialVersionUID = 1L;

	public OrderException(String msg) {
		super(msg);
	}

}
