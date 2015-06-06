package filescript;

public abstract class FileScriptException extends Exception {
	private static final long serialVersionUID = 1L;
	final static String ERROR = "ERROR", WARNING = "Warning in line ";
	
	public FileScriptException(String msg) {
		super(msg);
	}
}
