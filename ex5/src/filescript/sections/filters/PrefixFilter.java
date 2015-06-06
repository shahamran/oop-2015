package filescript.sections.filters;

import java.io.File;

public class PrefixFilter extends NameFilter {
	
	public PrefixFilter(String prefix) {
		this.prefix = prefix;
	}
	
	@Override
	public boolean isPass(File inFile) {
		if (inFile.exists()) {
			if (inFile.isFile()) {
				return (inFile.getName().startsWith(prefix));
			}
		}
		return false;
	}

	@Override
	public int getExpectedNumberOfValues() {
		return ONE_VALUE_FILTER_LENGTH;
	}

}
