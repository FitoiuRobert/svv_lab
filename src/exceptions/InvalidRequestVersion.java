package exceptions;

public class InvalidRequestVersion extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidRequestVersion() {
		super("Invalid request version!");
	}
}
