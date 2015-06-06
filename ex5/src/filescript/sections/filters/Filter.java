package filescript.sections.filters;
import java.io.*;

public interface Filter {
	public static final int NO_VALUES_FILTER_LENGTH = 1,
							ONE_VALUE_FILTER_LENGTH = 2, 
							TWO_VALUES_FILTER_LENGTH = 3;
	public boolean isPass(File f);
	public int getExpectedNumberOfValues();
}
