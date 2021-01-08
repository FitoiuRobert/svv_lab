package exceptions;

public class InvalidPortRangeException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPortRangeException() {
		super("Port out of range!");
	}
}
