package org.example.exception;

public class UserRegistrationIsPresentException extends Exception{
    public UserRegistrationIsPresentException() {
    }

    public UserRegistrationIsPresentException(String message) {
        super(message);
    }

    public UserRegistrationIsPresentException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRegistrationIsPresentException(Throwable cause) {
        super(cause);
    }

    public UserRegistrationIsPresentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
