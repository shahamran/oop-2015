package filescript.sections.filters;

import java.io.File;

public class ExecutableFilter extends PermissionFilter {
	
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

	@Override
	public int getExpectedNumberOfValues() {
		return ONE_VALUE_FILTER_LENGTH;
	}

}
