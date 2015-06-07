package oop.ex5.filescript.sections.filters;

import java.io.File;

/**
 * A filter that passes all files.
 * @author ransha
 */
public class AllFilter implements Filter {
	
	@Override
	public boolean isPass(File f) {
		return true;
	}

}
