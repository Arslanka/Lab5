package exceptions;

public class IOException extends RuntimeException {
    public IOException(String message) {
        super(message);
    }

    public IOException(String message, Throwable cause) {
        super(message, cause);
    }
}
