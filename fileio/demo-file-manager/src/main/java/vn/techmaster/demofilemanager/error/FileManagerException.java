package vn.techmaster.demofilemanager.error;

public class FileManagerException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FileManagerException(String message) {
		super(message);
	}

	public FileManagerException(String message, Throwable cause) {
		super(message, cause);
	}
}
