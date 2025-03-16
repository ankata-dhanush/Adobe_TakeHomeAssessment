package com.adobe.romannumerals.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler to manage exceptions across the application.
 * This class ensures that meaningful error responses are returned to the client.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles IllegalArgumentException and returns a BAD_REQUEST response.
     *
     * @param e the IllegalArgumentException thrown in the application
     * @return ResponseEntity containing the error message with HTTP 400 status
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    /**
     * Handles any general exceptions and returns an INTERNAL_SERVER_ERROR response.
     *
     * @param e the generic Exception thrown in the application
     * @return ResponseEntity containing a generic error message with HTTP 500 status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong!");
    }

    /**
     * Handles RomanNumericConversionException and returns an INTERNAL_SERVER_ERROR response.
     *
     * @param ex the custom exception for errors in Roman numeral conversion
     * @return ResponseEntity containing the specific error message with HTTP 500 status
     */
    @ExceptionHandler(RomanNumericConversionException.class)
    public ResponseEntity<String> handleRuntimeException(RomanNumericConversionException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
