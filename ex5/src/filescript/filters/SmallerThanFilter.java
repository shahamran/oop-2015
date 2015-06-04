package filescript.filters;

public class SmallerThanFilter extends SizeFilter {
	
	public SmallerThanFilter(double valInKB) throws BadFilterValueException {
		long valInLong = Double.doubleToLongBits(valInKB * super.BYTES_IN_KB());
		if (valInLong < 0)
			throw new BadFilterValueException();
		upperVal = valInLong;
	}
}
