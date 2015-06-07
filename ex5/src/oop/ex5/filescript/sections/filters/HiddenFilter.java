package oop.ex5.filescript.sections.filters;

import java.io.File;

/**
 * A filter that passes files with a given permission value.
 * @author ransha
 */
public class HiddenFilter extends PermissionFilter {
	
	/**
	 * @param yesNo Accepts only "YES" or "NO" values.
	 * @throws BadFilterValueException
	 */
	public HiddenFilter(String yesNo) throws BadFilterValueException {
		super(yesNo);
	}
	
	@Override
	public boolean isPass(File inFile) {
		if (inFile.exists()) {
			if (inFile.isFile()) {
				return inFile.isHidden() == myVal;
			}
		} 
		return false;
	}
}


