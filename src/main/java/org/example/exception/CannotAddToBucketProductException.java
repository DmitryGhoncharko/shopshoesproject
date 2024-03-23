package org.example.exception;

public class CannotAddToBucketProductException extends Exception{
    public CannotAddToBucketProductException() {
    }

    public CannotAddToBucketProductException(String message) {
        super(message);
    }

    public CannotAddToBucketProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotAddToBucketProductException(Throwable cause) {
        super(cause);
    }

    public CannotAddToBucketProductException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
