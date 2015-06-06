package filescript.sections.filters;

import filescript.TypeOneException;

public abstract class FilterException extends TypeOneException {
	private static final long serialVersionUID = 1L;

	public FilterException() {
		super("Error with filter");
	}
	
	public FilterException(String msg) {
		super(msg);
	}
	
}
