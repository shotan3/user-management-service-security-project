package com.example.user.management.exception.handler;

import com.example.user.management.exception.custom.DuplicateResourceConflictException;
import com.example.user.management.exception.custom.InvalidInputFormatException;
import com.example.user.management.exception.custom.PasswordUpdateNotAllowedException;
import com.example.user.management.exception.custom.ResourceNotFoundException;
import com.example.user.management.exception.custom.UnauthorizedRequestException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({DuplicateResourceConflictException.class, PasswordUpdateNotAllowedException.class})
    public ResponseEntity<Void> handleDuplicateResourceException() {
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({InvalidInputFormatException.class, IllegalArgumentException.class})
    public ResponseEntity<Void> handleInvalidInputException() {
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Void> handleResourceNotFoundExceptions() {
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UnauthorizedRequestException.class, BadCredentialsException.class, ExpiredJwtException.class})
    public ResponseEntity<Void> handleAuthorizationExceptions() {
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

}
