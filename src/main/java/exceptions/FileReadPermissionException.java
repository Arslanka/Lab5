package exceptions;

public class FileReadPermissionException extends IOException {
    public FileReadPermissionException(String message) {
        super(message);
    }

    public FileReadPermissionException(String message, Throwable cause) {
        super(message, cause);
    }
}
