package oop.ex5.filescript.filters;

import java.io.File;

/**
 * A general filter that passes files according to their permissions attributes.
 * @author ransha
 *
 */
public abstract class PermissionFilter implements Filter {
	protected static final String YES = "YES", NO = "NO";
	protected boolean myVal;
	
	/**
	 * @param yesNo Accepts only "YES" or "NO" values.
	 * @throws BadFilterValueException
	 */
	protected PermissionFilter(String yesNo) throws BadFilterValueException {
		if (yesNo.equals(YES)) {
			myVal = true;
		} else if (yesNo.equals(NO)) {
			myVal = false;
		} else {
			throw new BadFilterValueException(this, yesNo);
		}
	}
	
	@Override
	public abstract boolean isPass(File inFile);

}
