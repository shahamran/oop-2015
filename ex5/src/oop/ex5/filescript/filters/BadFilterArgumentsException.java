package oop.ex5.filescript.filters;

/**
 * A non-critical error that results from bad arguments that were given to the Filter object creator.
 * @author ransha
 */
public abstract class BadFilterArgumentsException extends FilterException {
	private static final long serialVersionUID = 1L;

	public BadFilterArgumentsException() {
		super("invalid filter arguments");
	}
	
	public BadFilterArgumentsException(String msg) {
		super(msg);
	}

}
