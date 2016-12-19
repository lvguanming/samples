package ramp.sample.jira.rest.java.client;

public class VersionNotPresentException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VersionNotPresentException() {
	}

	public VersionNotPresentException(String message) {
		super(message);
	}

	public VersionNotPresentException(Throwable cause) {
		super(cause);
	}

	public VersionNotPresentException(String message, Throwable cause) {
		super(message, cause);
	}

}
