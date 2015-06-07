package filescript.sections.orders;

/**
 * Thrown when a bad order name was given.
 * @author ransha
 */
public class BadOrderNameException extends OrderException {
	private static final long serialVersionUID = 1L;
	private String badVal;
	
	public BadOrderNameException(String badName) {
		super("Bad order name: " + badName);
		badVal = badName;
	}
	
	public String getBadName() {
		return badVal;
	}

}
