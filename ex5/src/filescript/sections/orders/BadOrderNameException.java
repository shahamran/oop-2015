package filescript.sections.orders;

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
