package org.example.exception;

public class CannotFindAllProductsException extends Exception{
    public CannotFindAllProductsException() {
    }

    public CannotFindAllProductsException(String message) {
        super(message);
    }

    public CannotFindAllProductsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotFindAllProductsException(Throwable cause) {
        super(cause);
    }

    public CannotFindAllProductsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
