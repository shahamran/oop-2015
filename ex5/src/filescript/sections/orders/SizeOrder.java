package filescript.sections.orders;

import java.io.File;

public class SizeOrder extends Order {
	@Override
	public int compare(File o1, File o2) {
		Long first = o1.length(), second = o2.length();
		int comparison = first.compareTo(second);
		if (comparison == ILLEGAL) {
			return o1.getAbsolutePath().compareTo(o2.getAbsolutePath());
		} else {
			return comparison;
		}
	}

}
