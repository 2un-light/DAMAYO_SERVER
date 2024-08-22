package com.damayoproject.damayo.domain.user.exception;
//비밀번호 확인
public class PasswordMismatchException extends RuntimeException{
    public PasswordMismatchException(String message){
        super(message);
    }
}
