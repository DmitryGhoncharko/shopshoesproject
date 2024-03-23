package org.example.exception;

public class ProductInvalidDataException extends Exception{
    public ProductInvalidDataException() {
    }

    public ProductInvalidDataException(String message) {
        super(message);
    }

    public ProductInvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductInvalidDataException(Throwable cause) {
        super(cause);
    }

    public ProductInvalidDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
