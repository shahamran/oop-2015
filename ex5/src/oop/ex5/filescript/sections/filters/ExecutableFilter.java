package oop.ex5.filescript.sections.filters;

import java.io.File;

/**
 * A filter that checks if the file is exectuable or not.
 * @author ransha
 */
public class ExecutableFilter extends PermissionFilter {
	
	/**
	 * @param yesNo only "YES" or "NO" values accepted
	 * @throws BadFilterValueException
	 */
	public ExecutableFilter(String yesNo) throws BadFilterValueException {
		super(yesNo);
	}
	
	@Override
	public boolean isPass(File inFile) {
		if (inFile.exists()) {
			if (inFile.isFile()) {
				return inFile.canExecute() == myVal;
			}
		} 
		return false;
	}
}
