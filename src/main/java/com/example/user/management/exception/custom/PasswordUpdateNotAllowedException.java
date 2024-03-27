package com.example.user.management.exception.custom;

public class PasswordUpdateNotAllowedException extends RuntimeException {

    public PasswordUpdateNotAllowedException() {
        super();
    }

    public PasswordUpdateNotAllowedException(String message) {
        super(message);
    }

}
