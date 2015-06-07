package oop.ex5.filescript.sections.filters;

import java.io.File;

/**
 * A filter that passes files between 2 given values.
 * @author ransha
 */
public class BetweenFilter extends SizeFilter {
	private boolean EQUALS_ACCEPTED = true;
	
	/**
	 * @param lowerKB The lower size limit in KBs.
	 * @param upperKB The upper size limit in KBs.
	 * @throws BadFilterValueException
	 */
	public BetweenFilter(double lowerKB, double upperKB) throws BadFilterValueException {
		lowerVal = super.convertKBtoBytes(lowerKB);
		upperVal = super.convertKBtoBytes(upperKB);
		if (lowerVal > upperVal || lowerVal < 0) { // invalid values - negative or opposite order
			String msg = "lower: " + String.valueOf(lowerVal) + 
						 " upper: " + String.valueOf(upperVal);
			throw new BadFilterValueException(this, msg);
		}
	}

	@Override
	public boolean isPass(File inFile) {
		return super.isPass(inFile, EQUALS_ACCEPTED);
	}
}
