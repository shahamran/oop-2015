package filescript.sections.orders;

import java.io.File;

public class AbsOrder extends Order {

	@Override
	public int compare(File o1, File o2) {
		if (isLegalComparison(o1,o2)) {
			return o1.getAbsolutePath().compareTo(o2.getAbsolutePath());
		}
		return ILLEGAL;
	}
	
	

}
