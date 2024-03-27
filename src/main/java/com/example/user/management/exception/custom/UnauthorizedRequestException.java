package com.example.user.management.exception.custom;

public class UnauthorizedRequestException extends RuntimeException {

    public UnauthorizedRequestException() {
        super();
    }

    public UnauthorizedRequestException(String message) {
        super(message);
    }

}
