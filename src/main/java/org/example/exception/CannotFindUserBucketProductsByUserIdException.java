package org.example.exception;

public class CannotFindUserBucketProductsByUserIdException extends Exception{
    public CannotFindUserBucketProductsByUserIdException() {
    }

    public CannotFindUserBucketProductsByUserIdException(String message) {
        super(message);
    }

    public CannotFindUserBucketProductsByUserIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotFindUserBucketProductsByUserIdException(Throwable cause) {
        super(cause);
    }

    public CannotFindUserBucketProductsByUserIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
