package filescript.filters;

import java.io.File;

public class GreaterThanFilter implements Filter {
	private long thresholdVal;
	private static final int BYTES_IN_KB = 1024;
	
	public GreaterThanFilter(double valInKB) {
		long valInLong = Double.doubleToLongBits(valInKB * BYTES_IN_KB);
		thresholdVal = valInLong;
	}
	
	public boolean isPass(File inFile) {
		if (inFile.isFile()) {
			if (inFile.length() > thresholdVal) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
