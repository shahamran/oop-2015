package oop.ex5.filescript.sections.filters;

import java.io.File;

/**
 * A filter that passes file smaller than a given value.
 * @author ransha
 *
 */
public class SmallerThanFilter extends SizeFilter {
	private boolean EQUALS_ACCEPTED = false;
	
	/**
	 * @param valInKB The biggest value passed files can be.
	 * @throws BadFilterValueException
	 */
	public SmallerThanFilter(double valInKB) throws BadFilterValueException {
		long valInLong = super.convertKBtoBytes(valInKB);
		if (valInLong < 0)
			throw new BadFilterValueException(this, String.valueOf(valInLong));
		upperVal = valInLong;
	}

	@Override
	public boolean isPass(File inFile) {
		return super.isPass(inFile, EQUALS_ACCEPTED);
	}
}
