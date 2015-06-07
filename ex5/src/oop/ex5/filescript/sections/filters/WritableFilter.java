package oop.ex5.filescript.sections.filters;

import java.io.File;

/**
 * A filter that passes files with a given Writable permissions
 * @author ransha
 */
public class WritableFilter extends PermissionFilter {
	
	public WritableFilter(String yesNo) throws BadFilterValueException {
		super(yesNo);
	}
	
	@Override
	public boolean isPass(File inFile) {
		if (inFile.exists()) {
			if (inFile.isFile()) {
				return inFile.canWrite() == myVal;
			}
		} 
		return false;
	}
}
