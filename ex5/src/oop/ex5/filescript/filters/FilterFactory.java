package oop.ex5.filescript.filters;

/**
 * This class is in charge of creating new filter objects according to given values.
 * @author ransha
 */
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
		// The filter name should always be the first argument.
		String filterName = values[0];
		int expectedArgsLength;
		// Switch statement uses an equivilent to s.equals() method and not reference comparison.
		switch (filterName) {
		case GREATER:
			expectedArgsLength = Filter.ONE_VALUE_FILTER_LENGTH;
			outFilter = new GreaterThanFilter(Double.parseDouble(values[FIRST_VAL]));
			break;
		case SMALLER:
			expectedArgsLength = Filter.ONE_VALUE_FILTER_LENGTH;
			outFilter = new SmallerThanFilter(Double.parseDouble(values[FIRST_VAL]));
			break;
		case BETWEEN:
			expectedArgsLength = Filter.TWO_VALUES_FILTER_LENGTH;
			double first = Double.parseDouble(values[FIRST_VAL]), 
				   second = Double.parseDouble(values[SECOND_VAL]);
			outFilter = new BetweenFilter(first,second);
			break;
		case FILE:
			expectedArgsLength = Filter.ONE_VALUE_FILTER_LENGTH;
			outFilter = new FileFilter(values[FIRST_VAL]);
			break;
		case CONTAINS:
			expectedArgsLength = Filter.ONE_VALUE_FILTER_LENGTH;
			outFilter = new ContainsFilter(values[FIRST_VAL]);
			break;
		case PREFIX:
			expectedArgsLength = Filter.ONE_VALUE_FILTER_LENGTH;
			outFilter = new PrefixFilter(values[FIRST_VAL]);
			break;
		case SUFFIX:
			expectedArgsLength = Filter.ONE_VALUE_FILTER_LENGTH;
			outFilter = new SuffixFilter(values[FIRST_VAL]);
			break;
		case WRITABLE:
			expectedArgsLength = Filter.ONE_VALUE_FILTER_LENGTH;
			outFilter = new WritableFilter(values[FIRST_VAL]);
			break;
		case EXECUTABLE:
			expectedArgsLength = Filter.ONE_VALUE_FILTER_LENGTH;
			outFilter = new ExecutableFilter(values[FIRST_VAL]);
			break;
		case HIDDEN:
			expectedArgsLength = Filter.ONE_VALUE_FILTER_LENGTH;
			outFilter = new HiddenFilter(values[FIRST_VAL]);
			break;
		case ALL:
			expectedArgsLength = Filter.NO_VALUES_FILTER_LENGTH;
			outFilter = new AllFilter();
			break;
			default:
				// If other filter name was given, throw an exception.
				throw new BadFilterNameException(filterName);
		}
		
		if (!isLegalValues(expectedArgsLength, values)) 
			throw new BadFilterValueException();
		if (isNegFilter(expectedArgsLength, values))
			outFilter = new NegFilter(outFilter);
		return outFilter;
	}
	
	/**
	 * @param expectedNumberOfValues The expected number of arguments.
	 * @param args The string args that were given.
	 * @return True if the right number of values was given, false otherwise.
	 */
	private static boolean isLegalValues(int expectedNumberOfValues, String[] args) {
		return (args.length == expectedNumberOfValues ||
				isNegFilter(expectedNumberOfValues,args));
	}
	
	/**
	 * @param expectedNumberOfValues The expected number of arguments.
	 * @param args The string args that were given.
	 * @return True if exactly one more argument was given, and the argument is "NOT"
	 */
	private static boolean isNegFilter(int expectedNumberOfValues, String[] args) {
		return (args.length == expectedNumberOfValues + 1) &&
				(args[expectedNumberOfValues].equals(NOT));
	}
	
}
