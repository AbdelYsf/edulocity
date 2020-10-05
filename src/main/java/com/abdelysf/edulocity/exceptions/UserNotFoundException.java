package com.abdelysf.edulocity.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String s, Exception e) {
        super(s, e);
    }

    public UserNotFoundException(String s) {
        super(s);
    }
}