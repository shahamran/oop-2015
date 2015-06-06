package filescript;

public class IllegalArgumentsException extends FileScriptException {
	private static final long serialVersionUID = 1L;

	public IllegalArgumentsException(String msg) {
		super("Invalid number of arguments given.");
	}

}
