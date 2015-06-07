package filescript.sections.filters;

import java.io.File;

/**
 * A decorator class that has a filter and returns the opposite of what it would have returned.
 * @author ransha
 */
public class NegFilter implements Filter {
	Filter originalFilter;
	/**
	 * @param filter The filter to negate.
	 * @throws FilterException
	 */
	public NegFilter(Filter filter) throws FilterException {
		originalFilter = filter;
	}
	
	@Override
	public boolean isPass(File file) {
		return (!originalFilter.isPass(file));
	}
}
