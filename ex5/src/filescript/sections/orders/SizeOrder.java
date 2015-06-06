package filescript.sections.orders;

import java.io.File;

public class SizeOrder extends Order {
	private static final int SMALLER = -1, LARGER = 1;
	@Override
	public int compare(File o1, File o2) {
		if (isLegalComparison(o1,o2)) {
			return (o1.length() <= o2.length() ? SMALLER : LARGER);
		}
		return ILLEGAL;
	}

}
