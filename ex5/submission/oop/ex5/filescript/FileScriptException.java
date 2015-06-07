package filescript;

/**
 * The mother of all exceptions in this exercise.
 * @author ransha
 *
 */
public abstract class FileScriptException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public FileScriptException(String msg) {
		super(msg);
	}
}
