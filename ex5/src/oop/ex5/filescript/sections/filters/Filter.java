package oop.ex5.filescript.sections.filters;
import java.io.*;

/**
 * The filter interface.
 * @author ransha
 */
public interface Filter {
	public static final int NO_VALUES_FILTER_LENGTH = 1,
							ONE_VALUE_FILTER_LENGTH = 2, 
							TWO_VALUES_FILTER_LENGTH = 3;
	/**
	 * @param f The file to check
	 * @return True if the filter passes this file, False otherwise.
	 */
	public boolean isPass(File f);
}
