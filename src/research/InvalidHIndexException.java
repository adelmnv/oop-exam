package research;

public class InvalidHIndexException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidHIndexException(String message) {
        super(message);
    }
}
