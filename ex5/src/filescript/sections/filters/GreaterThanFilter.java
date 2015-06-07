package filescript.sections.filters;

/**
 * A filter that passes files with more than a given size.
 * @author ransha
 */
public class GreaterThanFilter extends SizeFilter {
	
	public GreaterThanFilter(double valInKB) throws BadFilterValueException {
		long valInLong = Double.doubleToLongBits(valInKB * super.BYTES_IN_KB());
		if (valInLong < 0)
			throw new BadFilterValueException(this, String.valueOf(valInLong));
		lowerVal = valInLong;
	}
}
