package com.damayoproject.damayo.domain.user.exception;

//이메일 중복
public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
