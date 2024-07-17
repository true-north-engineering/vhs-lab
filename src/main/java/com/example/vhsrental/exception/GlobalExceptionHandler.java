package com.example.vhsrental.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Locale;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request, Locale locale) {
        String message = messageSource.getMessage("error.resource_not_found", new Object[]{ex.getMessage()}, locale);
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RentalException.class)
    public ResponseEntity<String> handleRentalException(RentalException ex, WebRequest request, Locale locale) {
        String message = messageSource.getMessage("error.rental_conflict", null, locale);
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(VHSAssociatedWithRentalException.class)
    public ResponseEntity<String> handleVHSAssociatedWithRentalException(VHSAssociatedWithRentalException ex, WebRequest request, Locale locale) {
        String message = messageSource.getMessage("error.vhs_associated_with_rental", new Object[]{ex.getMessage()}, locale);
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request, Locale locale) {
        String message = messageSource.getMessage("error.internal_server_error", null, locale);
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
