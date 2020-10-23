package com.haris.linkanalyzer.exception;

public class UserNotFoundException extends RuntimeException {
    private static final String USER_NOT_FOUND_MESSAGE = "User Not Found";

    public UserNotFoundException() {
        super(USER_NOT_FOUND_MESSAGE);
    }
}
