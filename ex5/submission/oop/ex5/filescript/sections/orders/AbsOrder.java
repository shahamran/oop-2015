package filescript.sections.orders;

import java.io.File;

/**
 * The default order for files comparison - Orders by absolute path.
 * @author ransha
 */
public class AbsOrder extends Order {

	@Override
	public int compare(File o1, File o2) {
		return o1.getAbsolutePath().compareTo(o2.getAbsolutePath());
	}
	
	

}
