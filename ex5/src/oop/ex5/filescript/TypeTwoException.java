package oop.ex5.filescript;

/**
 * A critical error in the program.
 * @author ransha
 */
public abstract class TypeTwoException extends FileScriptException {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param msg The error message.
	 */
	public TypeTwoException(String msg) {
		super(msg);
	}

}
