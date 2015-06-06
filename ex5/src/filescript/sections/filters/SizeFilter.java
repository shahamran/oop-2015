package filescript.sections.filters;

import java.io.File;

public abstract class SizeFilter implements Filter {
	private static final long NOT_SET = -1;
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
				if (lowerVal != NOT_SET) {
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
