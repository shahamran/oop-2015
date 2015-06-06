package filescript.sections.filters;

import java.io.File;

public abstract class NameFilter implements Filter {
	protected String suffix, prefix, fileName, searchVal;
	
	@Override
	public abstract boolean isPass(File inFile);

}
