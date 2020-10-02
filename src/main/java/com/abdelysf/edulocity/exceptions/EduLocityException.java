package com.abdelysf.edulocity.exceptions;

import org.springframework.mail.MailException;

public class EduLocityException extends RuntimeException {
    public EduLocityException(String s, Exception e) {
        super(s, e);
    }

    public EduLocityException(String s) {
        super(s);
    }
}
