package filescript.sections.filters;

import java.io.File;

public class NegFilter implements Filter {
	Filter originalFilter;
	
	public NegFilter(Filter filter) throws FilterException {
		originalFilter = filter;
	}
	
	@Override
	public boolean isPass(File file) {
		return (!originalFilter.isPass(file));
	}

	@Override
	public int getExpectedNumberOfValues() {
		return originalFilter.getExpectedNumberOfValues();
	}
	
}
