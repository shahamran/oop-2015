package filescript.sections.filters;

import java.io.File;

/**
 * A general filter that passese files according to their size attributes.
 * @author ransha
 */
public abstract class SizeFilter implements Filter {
	private static final Long NOT_SET = new Long(-1); // A new object for reference-comparison.
	protected long lowerVal = NOT_SET, upperVal = NOT_SET;
	private static final int BYTES_IN_KB = 1024;
	
	protected static int BYTES_IN_KB() {
		return BYTES_IN_KB;
	}
	
	@Override
	public boolean isPass(File inFile) {
		if (inFile.exists()) {
			if (inFile.isFile()) {
				boolean isPass = true;
				if (lowerVal != NOT_SET) { // MEANS - the lower val is not set
					isPass = lowerVal < inFile.length();
				} 
				if (upperVal != NOT_SET) {
					isPass = isPass && upperVal > inFile.length();
				}
				return isPass;
			}
		}
		return false;
	}

}
