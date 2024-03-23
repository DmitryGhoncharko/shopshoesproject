package org.example.exception;

public class CannotPayUser extends Exception {
    public CannotPayUser() {
    }

    public CannotPayUser(String message) {
        super(message);
    }

    public CannotPayUser(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotPayUser(Throwable cause) {
        super(cause);
    }

    public CannotPayUser(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
