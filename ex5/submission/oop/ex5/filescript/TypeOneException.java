package filescript;

/**
 * A non-critical error in the program. Can store the line number in which the error has occured.
 * @author ransha
 */
public abstract class TypeOneException extends FileScriptException {
	private static final long serialVersionUID = 1L;
	private int lineNum = -1;
	
	/**
	 * @param msg The error message
	 */
	public TypeOneException(String msg) {
		super(msg);
	}
	
	/**
	 * @param errLine The line in the commands file in which the error has occured
	 */
	public void setLineNumber(int errLine) {
		lineNum = errLine;
	}
	
	/**
	 * @return The line in the commands file in which the error has occured
	 */
	public int getLineNumber() {
		return lineNum;
	}

}
