package filescript.filters;
import java.io.*;

public class FiltersFactory {
	private static final String GREATER = "greater_than", SMALLER = "smaller_than", BETWEEN = "between",
							FILE = "file", CONTAINS = "contains", PREFIX = "prefix", SUFFIX = "suffix",
							WRITABLE = "writable", EXECUTABLE = "executable", HIDDEN = "hidden", ALL = "all";
	public static Filter createFilter(String filterString, FilterValue[] values) {
		switch (filterString) {
		case GREATER:
			double val = (Double) values[0].getVal();
			return new GreaterThanFilter(val);
		}
		return null;
	}
}
