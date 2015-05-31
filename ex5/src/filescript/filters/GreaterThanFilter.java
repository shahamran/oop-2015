package filescript.filters;

import java.io.File;

public class GreaterThanFilter implements Filter {
	private long thresholdVal;
	
	public GreaterThanFilter(long thanWhat) {
		thresholdVal = thanWhat;
	}
	
	public boolean isPass(File f) throws FilterException {
		// TODO Auto-generated method stub
		if (f.isFile()) {
			if (f.length() > thresholdVal) {
				return true;
			} else {
				return false;
			}
		} else {
			throw new FilterException();
		}
	}

}
