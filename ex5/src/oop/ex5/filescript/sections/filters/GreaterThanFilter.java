package oop.ex5.filescript.sections.filters;

import java.io.File;

/**
 * A filter that passes files with more than a given size.
 * @author ransha
 */
public class GreaterThanFilter extends SizeFilter {
	private boolean EQUALS_ACCEPTED = false;
	
	/**
	 * @param valInKB The size in KB that this filter passes files larger than it.
	 * @throws BadFilterValueException
	 */
	public GreaterThanFilter(double valInKB) throws BadFilterValueException {
		long valInLong = super.convertKBtoBytes(valInKB);
		if (valInLong < 0)
			throw new BadFilterValueException(this, String.valueOf(valInLong));
		lowerVal = valInLong;
	}

	@Override
	public boolean isPass(File inFile) {
		return isPass(inFile, EQUALS_ACCEPTED);
	}
}
