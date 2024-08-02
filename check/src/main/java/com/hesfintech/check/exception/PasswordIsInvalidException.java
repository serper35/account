package com.hesfintech.check.exception;

public class PasswordIsInvalidException extends RuntimeException {
    public PasswordIsInvalidException(String message) {
        super(message);
    }
}
