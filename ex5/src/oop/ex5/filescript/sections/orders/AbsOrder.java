package oop.ex5.filescript.sections.orders;

import java.io.File;

/**
 * The default order for files comparison - Orders by absolute path.
 * @author ransha
 */
public class AbsOrder implements Order {

	@Override
	public int compare(File o1, File o2) {
		String path1 = o1.getAbsolutePath(),
			   path2 = o2.getAbsolutePath();
		return path1.compareTo(path2);
	}
	
	

}
