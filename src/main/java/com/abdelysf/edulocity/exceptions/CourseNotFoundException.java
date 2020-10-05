package com.abdelysf.edulocity.exceptions;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String s, Exception e) {
        super(s, e);
    }

    public CourseNotFoundException(String s) {
        super(s);
    }
}

