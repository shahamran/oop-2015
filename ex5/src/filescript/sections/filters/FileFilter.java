package filescript.sections.filters;

import java.io.File;

public class FileFilter extends NameFilter {
	
	public FileFilter(String file) {
		this.fileName = file;
	}
	
	@Override
	public boolean isPass(File inFile) {
		if (inFile.exists()) {
			if (inFile.isFile()) {
				return (inFile.getName().equals(this.fileName));
			}
		}
		return false;
	}

	@Override
	public int getExpectedNumberOfValues() {
		return ONE_VALUE_FILTER_LENGTH;
	}

}
