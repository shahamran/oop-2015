package filescript.sections.filters;

public class BetweenFilter extends SizeFilter {
	
	public BetweenFilter(double lowerKB, double upperKB) throws BadFilterValueException {
		lowerVal = (long) (lowerKB * super.BYTES_IN_KB());
		upperVal = (long) (upperKB * super.BYTES_IN_KB());
		if (lowerVal > upperVal || lowerVal < 0){
			String msg = "lower: " + String.valueOf(lowerVal) + 
						 " upper: " + String.valueOf(upperVal);
			throw new BadFilterValueException(this, msg);
		}
	}

	@Override
	public int getExpectedNumberOfValues() {
		return TWO_VALUES_FILTER_LENGTH;
	}
}
