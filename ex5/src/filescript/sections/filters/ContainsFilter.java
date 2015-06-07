package filescript.sections.filters;

import java.io.File;

/**
 * A filter that checks if the filename contains a given string
 * @author ransha
 */
public class ContainsFilter extends NameFilter {
	
	/**
	 * @param containsWhat The string to check.
	 */
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
}
