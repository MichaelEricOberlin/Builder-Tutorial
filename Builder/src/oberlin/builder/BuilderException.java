package oberlin.builder;

/**
 * Exception thrown from within a build process, involving the provided code. Note that this is
 * a RuntimeException extension; it is specifically designed to be throwable from within any
 * extension of any class or interface without requiring a catch. Thus, these should not
 * be used lightly.
 * 
 * @author © Michael Eric Oberlin Nov 11, 2014
 *
 */
public class BuilderException extends RuntimeException {

	public BuilderException() {
		// TODO Auto-generated constructor stub
	}

	public BuilderException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BuilderException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public BuilderException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BuilderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
