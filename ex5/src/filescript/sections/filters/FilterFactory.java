package filescript.sections.filters;

public class FilterFactory {
	private static final String GREATER = "greater_than", SMALLER = "smaller_than", BETWEEN = "between",
							FILE = "file", CONTAINS = "contains", PREFIX = "prefix", SUFFIX = "suffix",
							WRITABLE = "writable", EXECUTABLE = "executable", HIDDEN = "hidden",
							NOT = "NOT", ALL = "all";
	private static final int FIRST_VAL = 1, SECOND_VAL = 2;
	
	public static Filter createFilter(String[] values) throws FilterException {
		if (values.length == 0)
			return null;
		
		Filter outFilter = null;
		double filterVal,filterSecond;
		String filterName = values[0];
		int expectedArgsLength;
		
		if (filterName.equals(GREATER)) {
			expectedArgsLength = Filter.ONE_VALUE_FILTER_LENGTH;
			if (isLegalValues(expectedArgsLength,values)) {
				filterVal = Double.parseDouble(values[FIRST_VAL]);
				outFilter = new GreaterThanFilter(filterVal);
			} else {
				throw new BadFilterArgumentsException();
			}
		} else if (filterName.equals(SMALLER)) {
			expectedArgsLength = Filter.ONE_VALUE_FILTER_LENGTH;
			if (isLegalValues(expectedArgsLength,values)) {
				filterVal = Double.parseDouble(values[FIRST_VAL]);
				outFilter = new SmallerThanFilter(filterVal);
			} else {
				throw new BadFilterArgumentsException();
			}
		} else if (filterName.equals(BETWEEN)) {
			expectedArgsLength = Filter.TWO_VALUES_FILTER_LENGTH;
			if (isLegalValues(expectedArgsLength,values)) {
				filterVal = Double.parseDouble(values[FIRST_VAL]);
				filterSecond = Double.parseDouble(values[SECOND_VAL]);
				outFilter = new BetweenFilter(filterVal,filterSecond);
			} else {
				throw new BadFilterArgumentsException();
			}
		} else if (filterName.equals(FILE)) {
			expectedArgsLength = Filter.ONE_VALUE_FILTER_LENGTH;
			if (isLegalValues(expectedArgsLength,values)) {
				outFilter = new FileFilter(values[FIRST_VAL]);
			} else {
				throw new BadFilterArgumentsException();
			}
		} else if (filterName.equals(CONTAINS)) {
			expectedArgsLength = Filter.ONE_VALUE_FILTER_LENGTH;
			if (isLegalValues(expectedArgsLength,values)) {
				outFilter = new ContainsFilter(values[FIRST_VAL]);
			} else {
				throw new BadFilterArgumentsException();
			}
		} else if (filterName.equals(PREFIX)) {
			expectedArgsLength = Filter.ONE_VALUE_FILTER_LENGTH;
			if (isLegalValues(expectedArgsLength,values)) {
				outFilter = new PrefixFilter(values[FIRST_VAL]);
			} else {
				throw new BadFilterArgumentsException();
			}
		} else if (filterName.equals(SUFFIX)) {
			expectedArgsLength = Filter.ONE_VALUE_FILTER_LENGTH;
			if (isLegalValues(expectedArgsLength,values)) {
				outFilter = new SuffixFilter(values[FIRST_VAL]);
			} else {
				throw new BadFilterArgumentsException();
			}
		} else if (filterName.equals(WRITABLE)) {
			expectedArgsLength = Filter.ONE_VALUE_FILTER_LENGTH;
			if (isLegalValues(expectedArgsLength,values)) {
				outFilter = new WritableFilter(values[FIRST_VAL]);
			} else {
				throw new BadFilterArgumentsException();
			}
		} else if (filterName.equals(EXECUTABLE)) {
			expectedArgsLength = Filter.ONE_VALUE_FILTER_LENGTH;
			if (isLegalValues(expectedArgsLength,values)) {
				outFilter = new ExecutableFilter(values[FIRST_VAL]);
			} else {
				throw new BadFilterArgumentsException();
			}
		} else if (filterName.equals(HIDDEN)) {
			expectedArgsLength = Filter.ONE_VALUE_FILTER_LENGTH;
			if (isLegalValues(expectedArgsLength,values)) {
				outFilter = new HiddenFilter(values[FIRST_VAL]);
			} else {
				throw new BadFilterArgumentsException();
			}
		} else if (filterName.equals(ALL)) {
			expectedArgsLength = Filter.ONE_VALUE_FILTER_LENGTH;
			if (isLegalValues(expectedArgsLength,values)) {
				outFilter = new AllFilter();
			} else {
				throw new BadFilterArgumentsException();
			}
		} else {
			throw new BadFilterNameException(filterName);
		}
		
		if (isNegFilter(expectedArgsLength,values)) {
			outFilter = new NegFilter(outFilter);
		}
		
		return outFilter;	
	}
	
	private static boolean isLegalValues(int expectedNumberOfValues, String[] args) {
		return (args.length == expectedNumberOfValues ||
				isNegFilter(expectedNumberOfValues,args));
	}
	
	private static boolean isNegFilter(int expectedNumberOfValues, String[] args) {
		return (args.length == expectedNumberOfValues + 1) &&
				(args[expectedNumberOfValues].equals(NOT));
	}
	
}
