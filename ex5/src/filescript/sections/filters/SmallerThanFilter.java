package filescript.sections.filters;

public class SmallerThanFilter extends SizeFilter {
	
	public SmallerThanFilter(double valInKB) throws BadFilterValueException {
		long valInLong = Double.doubleToLongBits(valInKB * super.BYTES_IN_KB());
		if (valInLong < 0)
			throw new BadFilterValueException(this, String.valueOf(valInLong));
		upperVal = valInLong;
	}

	@Override
	public int getExpectedNumberOfValues() {
		return ONE_VALUE_FILTER_LENGTH;
	}
}
