package filescript.sections.orders;

import java.io.File;

/**
 * Orders files by size.
 * @author ransha
 */
public class SizeOrder extends AbsOrder {

	@Override
	public int compare(File o1, File o2) {
		Long l1 = o1.length(), l2 = o2.length();
		int comparison = l1.compareTo(l2);
		if (comparison == ILLEGAL) {
			return super.compare(o1,o2);
		} else {
			return comparison;
		}
	}

}
