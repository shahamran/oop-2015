package filescript.filters;

import java.io.File;

public class NegFilter implements Filter {
	Filter originalFilter;
	
	public NegFilter(Filter filter) throws FilterException {
		if (filter == null) {
			throw new FilterException();
		}
		originalFilter = filter;
	}
	
	@Override
	public boolean isPass(File file) {
		return (!originalFilter.isPass(file));
	}
	
}
