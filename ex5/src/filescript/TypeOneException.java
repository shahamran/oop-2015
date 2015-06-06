package filescript;

public abstract class TypeOneException extends FileScriptException {
	private static final long serialVersionUID = 1L;
	protected int errLine;
	
	public TypeOneException(String msg) {
		super(msg);
	}
	
	public void setLineNumber(int lineNum) {
		errLine = lineNum;
	}
	public int getErrorLine() {
		return errLine;
	}

}
