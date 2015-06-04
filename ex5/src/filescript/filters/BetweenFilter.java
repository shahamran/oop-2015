package filescript.filters;

public class BetweenFilter extends SizeFilter {
	
	public BetweenFilter(double lowerKB, double upperKB) throws BadFilterValueException {
		lowerVal = Double.doubleToLongBits(lowerKB * super.BYTES_IN_KB());
		upperVal = Double.doubleToLongBits(upperKB * super.BYTES_IN_KB());
		if (lowerVal > upperVal || lowerVal < 0)
			throw new BadFilterValueException();
	}
}
