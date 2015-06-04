package filescript.filters;

import java.io.File;

public abstract class PermissionFilter implements Filter {
	protected static final String YES = "YES", NO = "NO";
	protected boolean myVal;
	
	protected PermissionFilter(String yesNo) throws BadFilterValueException {
		if (yesNo.equals(YES)) {
			myVal = true;
		} else if (yesNo.equals(NO)) {
			myVal = false;
		} else {
			throw new BadFilterValueException();
		}
	}
	
	@Override
	public abstract boolean isPass(File inFile);

}
