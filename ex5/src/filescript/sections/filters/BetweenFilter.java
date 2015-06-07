package filescript.sections.filters;

/**
 * A filter that passes files between 2 given values.
 * @author ransha
 */
public class BetweenFilter extends SizeFilter {
	
	/**
	 * @param lowerKB The lower size limit in KBs.
	 * @param upperKB The upper size limit in KBs.
	 * @throws BadFilterValueException
	 */
	public BetweenFilter(double lowerKB, double upperKB) throws BadFilterValueException {
		lowerVal = Double.doubleToLongBits(lowerKB * super.BYTES_IN_KB());
		upperVal = Double.doubleToLongBits(upperKB * super.BYTES_IN_KB());
		if (lowerVal > upperVal || lowerVal < 0) { // invalid values - negative or opposite order
			String msg = "lower: " + String.valueOf(lowerVal) + 
						 " upper: " + String.valueOf(upperVal);
			throw new BadFilterValueException(this, msg);
		}
	}
}
