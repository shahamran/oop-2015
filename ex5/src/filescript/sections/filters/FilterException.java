package filescript.sections.filters;

import filescript.FileScriptException;

public abstract class FilterException extends FileScriptException {
	private static final long serialVersionUID = 1L;

	public FilterException(String msg) {
		super(msg);
	}
}
