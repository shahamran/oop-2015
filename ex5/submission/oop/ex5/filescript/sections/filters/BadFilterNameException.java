package filescript.sections.filters;

/**
 * Results from illegal name in the filter's name field.
 * @author ransha
 */
public class BadFilterNameException extends BadFilterArgumentsException {
	private static final long serialVersionUID = 1L;
	private String badVal;
	
	public BadFilterNameException(String badName) {
		super("Bad filter name: " + badName);
		badVal = badName;
	}
	
	public String getBadName() {
		return badVal;
	}

}
