package filescript.filters;

import java.io.File;

public class SuffixFilter extends NameFilter {

	public SuffixFilter(String suffix) {
		this.suffix = suffix;
	}
	
	@Override
	public boolean isPass(File inFile) {
		if (inFile.exists()) {
			if (inFile.isFile()) {
				return (inFile.getName().endsWith(suffix));
			}
		}
		return false;
	}

}
