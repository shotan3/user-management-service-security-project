package com.example.user.management.exception.custom;

public class InvalidInputFormatException extends RuntimeException {
    public InvalidInputFormatException(String message) {
        super(message);
    }

    public InvalidInputFormatException() {
        super();
    }

}
