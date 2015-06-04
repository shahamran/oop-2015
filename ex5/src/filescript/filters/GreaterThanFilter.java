package filescript.filters;

public class GreaterThanFilter extends SizeFilter {
	
	public GreaterThanFilter(double valInKB) throws BadFilterValueException {
		long valInLong = Double.doubleToLongBits(valInKB * super.BYTES_IN_KB());
		if (valInLong < 0)
			throw new BadFilterValueException();
		lowerVal = valInLong;
	}

}
