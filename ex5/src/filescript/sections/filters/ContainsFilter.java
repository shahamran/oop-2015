package filescript.sections.filters;

import java.io.File;

public class ContainsFilter extends NameFilter {

	public ContainsFilter(String containsWhat) {
		this.searchVal = containsWhat;
	}
	
	@Override
	public boolean isPass(File inFile) {
		if (inFile.exists()) {
			if (inFile.isFile()) {
				return (inFile.getName().contains(searchVal));
			}
		}
		return false;
	}

	@Override
	public int getExpectedNumberOfValues() {
		return ONE_VALUE_FILTER_LENGTH;
	}

}
