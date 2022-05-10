package exceptions;

public class FIleWritingException extends IOException {
    public FIleWritingException(String message) {
        super(message);
    }

    public FIleWritingException(String message, Throwable cause) {
        super(message, cause);
    }
}
