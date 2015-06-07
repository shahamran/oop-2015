package oop.ex5.filescript.parsing;

import oop.ex5.filescript.TypeTwoException;

/**
 * A critical error that results from bad commands file.
 * @author ransha
 */
public class BadCommandFileException extends TypeTwoException {
	private static final long serialVersionUID = 1L;
	
	public BadCommandFileException() {
		super("Bad command file");
	}
	
	public BadCommandFileException(String msg) {
		super(msg);
	}

}
