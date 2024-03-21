package com.example.user.management.exception.custom;

public class DuplicateResourceConflictException extends RuntimeException {
    public DuplicateResourceConflictException() {
        super();
    }

    public DuplicateResourceConflictException(String message) {
        super(message);
    }
}
