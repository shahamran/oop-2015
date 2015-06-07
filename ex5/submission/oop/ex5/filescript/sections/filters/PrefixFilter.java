package filescript.sections.filters;

import java.io.File;

/**
 * A filter that passes files with a given prefix.
 * @author ransha
 *
 */
public class PrefixFilter extends NameFilter {
	
	/**
	 * @param prefix the prefix to pass.
	 */
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
}
