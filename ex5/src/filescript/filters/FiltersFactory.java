package filescript.filters;

public class FiltersFactory {
	private static final String GREATER = "greater_than", SMALLER = "smaller_than", BETWEEN = "between",
							FILE = "file", CONTAINS = "contains", PREFIX = "prefix", SUFFIX = "suffix",
							WRITABLE = "writable", EXECUTABLE = "executable", HIDDEN = "hidden",
							NOT = "NOT", ALL = "all";
	private static final int FIRST_VAL = 1, SECOND_VAL = 2;
	public FiltersFactory() {
		
	}
	
	public static Filter createFilter(String[] values) throws FilterException {
		if (values.length <= 1)
			throw new FilterException();
		
		Filter outFilter = null;
		double filterVal,filterSecond;
		String filterName = values[0], lastArg = values[values.length - 1];
		
		if (filterName.equals(GREATER)) {
			filterVal = Double.parseDouble(values[FIRST_VAL]);
			outFilter = new GreaterThanFilter(filterVal);
		} else if (filterName.equals(SMALLER)) {
			filterVal = Double.parseDouble(values[FIRST_VAL]);
			outFilter = new SmallerThanFilter(filterVal);
		} else if (filterName.equals(BETWEEN)) {
			filterVal = Double.parseDouble(values[FIRST_VAL]);
			filterSecond = Double.parseDouble(values[SECOND_VAL]);
			outFilter = new BetweenFilter(filterVal,filterSecond);
		} else if (filterName.equals(FILE)) {
			outFilter = new FileFilter(values[FIRST_VAL]);
		} else if (filterName.equals(CONTAINS)) {
			outFilter = new ContainsFilter(values[FIRST_VAL]);
		} else if (filterName.equals(PREFIX)) {
			outFilter = new PrefixFilter(values[FIRST_VAL]);
		} else if (filterName.equals(SUFFIX)) {
			outFilter = new SuffixFilter(values[FIRST_VAL]);
		} else if (filterName.equals(WRITABLE)) {
			outFilter = new WritableFilter(values[FIRST_VAL]);
		} else if (filterName.equals(EXECUTABLE)) {
			outFilter = new ExecutableFilter(values[FIRST_VAL]);
		} else if (filterName.equals(HIDDEN)) {
			outFilter = new HiddenFilter(values[FIRST_VAL]);
		} else if (filterName.equals(ALL)) {
			outFilter = new AllFilter();
		} else {
			throw new BadFilterNameException();
		}
		
		if (lastArg.equals(NOT)) {
			outFilter = new NegFilter(outFilter);
		}
		
		return outFilter;	
	}
}
