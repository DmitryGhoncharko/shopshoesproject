package org.example.exception;

public class CannotUpdateProductException extends Exception{
    public CannotUpdateProductException() {
    }

    public CannotUpdateProductException(String message) {
        super(message);
    }

    public CannotUpdateProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotUpdateProductException(Throwable cause) {
        super(cause);
    }

    public CannotUpdateProductException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
