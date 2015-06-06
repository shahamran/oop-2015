package filescript.sections.orders;

import java.io.File;

public class TypeOrder extends Order {
	private static final String SEPERATOR = ".";
	
	@Override
	public int compare(File o1, File o2) {
		if (isLegalComparison(o1,o2)) {
			String[] fileName1 = o1.getName().split(SEPERATOR),
			         fileName2 = o2.getName().split(SEPERATOR);
			String suff1 = fileName1[fileName1.length - 1],
			       suff2 = fileName2[fileName2.length - 1];
			return suff1.compareTo(suff2);
		}
		return ILLEGAL;
	}

}
