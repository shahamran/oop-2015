package filescript.sections.filters;

import java.io.File;

/**
 * A general filter that uses strings to pass files.
 * @author ransha
 */
public abstract class NameFilter implements Filter {
	protected String suffix, prefix, fileName, searchVal;
	
	@Override
	public abstract boolean isPass(File inFile);

}
