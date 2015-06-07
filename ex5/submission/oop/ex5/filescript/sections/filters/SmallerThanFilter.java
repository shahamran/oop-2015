package filescript.sections.filters;

/**
 * A filter that passes file smaller than a given value.
 * @author ransha
 *
 */
public class SmallerThanFilter extends SizeFilter {
	
	/**
	 * @param valInKB The biggest value passed files can be.
	 * @throws BadFilterValueException
	 */
	public SmallerThanFilter(double valInKB) throws BadFilterValueException {
		long valInLong = (long) (valInKB * super.BYTES_IN_KB());
		if (valInLong < 0)
			throw new BadFilterValueException(this, String.valueOf(valInLong));
		upperVal = valInLong;
	}
}
