package oop.ex5.filescript.filters;

import java.io.File;

/**
 * A general filter that passese files according to their size attributes.
 * @author ransha
 */
public abstract class SizeFilter implements Filter {
	private static final Long NOT_SET = new Long(-1); // A new object for reference-comparison.
	protected long lowerVal = NOT_SET, upperVal = NOT_SET;
	private static final int BYTES_IN_KB = 1024;
	
	protected static long convertKBtoBytes(double KB) {
		return (long) (KB * BYTES_IN_KB);
	}
	
	protected boolean isPass(File inFile, boolean canBeEqual) {
		boolean isPass = true;
		if (lowerVal != NOT_SET) {
			isPass = lowerVal <= inFile.length();
		}
		if (upperVal != NOT_SET) {
			isPass = isPass && upperVal >= inFile.length();
		}
		if (canBeEqual) {
			return isPass;
		} else {
			return isPass && lowerVal != inFile.length() && upperVal != inFile.length();
		}
	}
	
	@Override
	public abstract boolean isPass(File inFile);

}
