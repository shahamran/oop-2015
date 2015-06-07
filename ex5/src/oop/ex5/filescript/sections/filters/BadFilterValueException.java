package oop.ex5.filescript.sections.filters;

/**
 * Results from bad filter values given.
 * @author ransha
 */
public class BadFilterValueException extends BadFilterArgumentsException {
	private static final long serialVersionUID = 1L;
	
	public BadFilterValueException() {
		super("Bad filter values given.");
	}
	
	public BadFilterValueException(Filter filter, String value) {
		super("Filter: " + filter.toString() + " can't accept value: " + value);
	}

}
