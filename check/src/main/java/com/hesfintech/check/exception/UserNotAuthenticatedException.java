package com.hesfintech.check.exception;

public class UserNotAuthenticatedException extends RuntimeException
{
    public UserNotAuthenticatedException(String message) {
        super(message);
    }
}
