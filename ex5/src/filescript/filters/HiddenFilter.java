package filescript.filters;

import java.io.File;

public class HiddenFilter extends PermissionFilter {
	
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


