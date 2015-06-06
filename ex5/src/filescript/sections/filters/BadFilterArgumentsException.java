package filescript.sections.filters;

public class BadFilterArgumentsException extends FilterException {
	private static final long serialVersionUID = 1L;

	public BadFilterArgumentsException() {
		super("invalid filter arguments");
	}
	
	public BadFilterArgumentsException(String msg) {
		super(msg);
	}

}
