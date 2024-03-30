package com.example.user.management.exception.custom;

public class InvalidJwtTokenException extends UnauthorizedRequestException {

    public InvalidJwtTokenException() {
        super();
    }

    public InvalidJwtTokenException(String message) {
        super(message);
    }

}
