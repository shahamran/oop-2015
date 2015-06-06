package filescript.sections.filters;

public class GreaterThanFilter extends SizeFilter {
	private static final int EXPECTED_VALUES = 2; 
	
	public GreaterThanFilter(double valInKB) throws BadFilterValueException {
		long valInLong = (long) (valInKB * super.BYTES_IN_KB());
		if (valInLong < 0)
			throw new BadFilterValueException(this, String.valueOf(valInLong));
		lowerVal = valInLong;
	}

	@Override
	public int getExpectedNumberOfValues() {
		return EXPECTED_VALUES;
	}

}
