package exceptions;

public class InvalidRequestMethodException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidRequestMethodException() {
		super("Request method not supported!");
	}
}
