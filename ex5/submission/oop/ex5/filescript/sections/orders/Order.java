package filescript.sections.orders;

import java.io.File;
import java.util.Comparator;

/**
 * An abstract order object (files comparator).
 * @author ransha
 */
public abstract class Order implements Comparator<File> {
	protected static int ILLEGAL = 0;
	
	protected static boolean isLegalComparison(File o1, File o2) {
		return (o1.exists() && o1.isFile() && o2.exists() && o2.isFile());
	}
	
}
