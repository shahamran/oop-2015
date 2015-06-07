package filescript.sections.filters;

import filescript.TypeOneException;

/**
 * A general class for non-critical filter exceptions.
 * @author ransha
 */
public abstract class FilterException extends TypeOneException {
	private static final long serialVersionUID = 1L;

	public FilterException(String msg) {
		super(msg);
	}
}
