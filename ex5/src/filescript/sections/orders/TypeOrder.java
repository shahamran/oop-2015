package filescript.sections.orders;

import java.io.File;

/**
 * Orders size by their extension (file.***)
 * @author ransha
 */
public class TypeOrder extends AbsOrder {
	private static final String SEPERATOR = ".";
	
	@Override
	public int compare(File o1, File o2) {
		String[] fileName1 = o1.getName().split(SEPERATOR),
		         fileName2 = o2.getName().split(SEPERATOR);
		String suff1 = fileName1[fileName1.length - 1],
		       suff2 = fileName2[fileName2.length - 1];
		int comparison = suff1.compareTo(suff2);
		if (comparison == ILLEGAL) {
			return super.compare(o1,o2);
		} else {
			return comparison;
		}
	}

}
