package org.example.exception;

public class CannotRemoveFromBucketException extends Exception{
    public CannotRemoveFromBucketException() {
    }

    public CannotRemoveFromBucketException(String message) {
        super(message);
    }

    public CannotRemoveFromBucketException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotRemoveFromBucketException(Throwable cause) {
        super(cause);
    }

    public CannotRemoveFromBucketException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
