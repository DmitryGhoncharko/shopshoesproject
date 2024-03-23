package org.example.exception;

public class CannotFindProductByIdException extends Exception{
    public CannotFindProductByIdException() {
    }

    public CannotFindProductByIdException(String message) {
        super(message);
    }

    public CannotFindProductByIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotFindProductByIdException(Throwable cause) {
        super(cause);
    }

    public CannotFindProductByIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
