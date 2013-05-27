package net.komunikator.server.exceptions;

/**
 * @author Piotr Rogowski<piotrekrogowski@gmail.com>
 */
public class NotConnectedException extends ConnectionException {
    public NotConnectedException() {
    }

    public NotConnectedException(String message) {
        super(message);
    }

    public NotConnectedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotConnectedException(Throwable cause) {
        super(cause);
    }

    public NotConnectedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
