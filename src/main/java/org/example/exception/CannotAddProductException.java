package org.example.exception;

public class CannotAddProductException extends Exception{
    public CannotAddProductException() {
    }

    public CannotAddProductException(String message) {
        super(message);
    }

    public CannotAddProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotAddProductException(Throwable cause) {
        super(cause);
    }

    public CannotAddProductException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
