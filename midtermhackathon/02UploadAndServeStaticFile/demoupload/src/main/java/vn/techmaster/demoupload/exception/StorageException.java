package vn.techmaster.demoupload.exception;

public class StorageException extends RuntimeException {
  private static final long serialVersionUID = 6288365963802085889L;

  public StorageException(String message) {
      super(message);
  }

  public StorageException(String message, Throwable cause) {
      super(message, cause);
  }
}
