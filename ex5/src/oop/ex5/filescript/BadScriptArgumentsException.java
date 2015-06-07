package oop.ex5.filescript;

/**
 * An error that results from bad arguments given to the filescript object. 
 * @author ransha
 */
public class BadScriptArgumentsException extends TypeTwoException {
	private static final long serialVersionUID = 1L;

	public BadScriptArgumentsException() {
		super("Bad script arguments.");
	}
	
	public BadScriptArgumentsException(String msg) {
		super(msg);
	}

}
