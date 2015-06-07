package oop.ex5.filescript.sections.filters;

import java.io.File;

/**
 * A filter that passes a certain file name
 * @author ransha
 */
public class FileFilter extends NameFilter {
	
	/**
	 * @param file The file name to pass
	 */
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
}
