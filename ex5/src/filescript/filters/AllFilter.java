package filescript.filters;

import java.io.File;

public class AllFilter implements Filter {

	@Override
	public boolean isPass(File f) {
		return true;
	}

}
