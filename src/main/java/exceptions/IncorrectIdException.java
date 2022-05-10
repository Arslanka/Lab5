package exceptions;

import java.io.IOException;

public class IncorrectIdException extends RuntimeException {

    public IncorrectIdException(String message) {
        super(message);
    }

    public IncorrectIdException(String message, Throwable cause) {
        super(message, cause);
    }

}
