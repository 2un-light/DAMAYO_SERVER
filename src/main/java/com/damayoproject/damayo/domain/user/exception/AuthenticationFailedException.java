package com.damayoproject.damayo.domain.user.exception;
//인증 실패
public class AuthenticationFailedException extends RuntimeException{
    public AuthenticationFailedException(String message) {
        super(message);
    }
}
