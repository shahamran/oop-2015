package filescript.sections.orders;

import filescript.FileScriptException;

public abstract class OrderException extends FileScriptException {
	private static final long serialVersionUID = 1L;

	public OrderException(String msg) {
		super(msg);
	}

}
