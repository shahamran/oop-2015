package filescript.sections.filters;

public class BadFilterValueException extends BadFilterArgumentsException {
	private static final long serialVersionUID = 1L;
	
	public BadFilterValueException(Filter filter, String value) {
		super("Filter: " + filter.toString() + " can't accept value: " + value);
	}

}
