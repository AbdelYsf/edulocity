package com.abdelysf.edulocity.exceptions;

public class SectionNotFoundException extends RuntimeException {
    public SectionNotFoundException(String s, Exception e) {
        super(s, e);
    }

    public SectionNotFoundException(String s) {
        super(s);
    }
}
