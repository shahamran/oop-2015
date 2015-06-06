package filescript.sections.filters;

import java.io.File;

public class AllFilter implements Filter {
	
	@Override
	public boolean isPass(File f) {
		return true;
	}

	@Override
	public int getExpectedNumberOfValues() {
		return NO_VALUES_FILTER_LENGTH;
	}

}
