package filescript.sections;

import filescript.TypeTwoException;

public class BadCommandFileException extends TypeTwoException {
	private static final long serialVersionUID = 1L;

	public BadCommandFileException(String msg) {
		super(msg);
	}

}
