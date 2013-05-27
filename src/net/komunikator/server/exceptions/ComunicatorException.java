package net.komunikator.server.exceptions;

/**
 * @author Piotr Rogowski<piotrekrogowski@gmail.com>
 */
public class ComunicatorException extends Exception {
    public ComunicatorException() {
    }

    public ComunicatorException(String message) {
        super(message);
    }

    public ComunicatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComunicatorException(Throwable cause) {
        super(cause);
    }

    public ComunicatorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
