package oop.ex5.filescript.orders;

import java.io.File;

/**
 * Orders size by their extension (file.***)
 * @author ransha
 */
public class TypeOrder extends AbsOrder {
	private static final String SEPERATOR = "\\.";
	
	@Override
	public int compare(File o1, File o2) {
		// Turn the file name to an array of strings that is splitted by "." strings.
		String[] fileName1 = o1.getName().split(SEPERATOR),
		         fileName2 = o2.getName().split(SEPERATOR);
		// Get the last argument of this array if the array was created, otherwise, use the file name.
		String suff1 = fileName1.length > 0 ? fileName1[fileName1.length - 1] : o1.getName();
		String suff2 = fileName2.length > 0 ? fileName2[fileName2.length - 1] : o2.getName();
		int comparison = suff1.compareTo(suff2);
		if (comparison == EQUALS) {
			return super.compare(o1,o2);
		} else {
			return comparison;
		}
	}

}
