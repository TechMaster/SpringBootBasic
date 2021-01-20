package vn.techmaster.demofilemanager.error;

public class FileManagerFileNotFoundException extends FileManagerException {
	private static final long serialVersionUID = 3620291730359871104L;

	public FileManagerFileNotFoundException(String message) {
		super(message);
	}

	public FileManagerFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
