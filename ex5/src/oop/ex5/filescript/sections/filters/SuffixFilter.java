package oop.ex5.filescript.sections.filters;

import java.io.File;

/**
 * A filter that passes files with a given suffix. 
 * @author ransha
 */
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
