package org.example.exception;

public class CannotDeleteProductException extends Exception{
    public CannotDeleteProductException() {
    }

    public CannotDeleteProductException(String message) {
        super(message);
    }

    public CannotDeleteProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotDeleteProductException(Throwable cause) {
        super(cause);
    }

    public CannotDeleteProductException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
