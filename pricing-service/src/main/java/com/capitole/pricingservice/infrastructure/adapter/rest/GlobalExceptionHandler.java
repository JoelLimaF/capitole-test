package com.capitole.pricingservice.infrastructure.adapter.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.capitole.pricingservice.application.exception.PriceNotFoundException;

/**
 * Global exception handler for the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
     * Handles PriceNotFoundExceptions.
     *
     * @param ex the exception
     * @return a ResponseEntity with a NOT_FOUND status and the exception message
     */
	@ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<String> handlePriceNotFoundException(PriceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
	
	/**
     * Handles MissingServletRequestParameterExceptions.
     *
     * @param ex the exception
     * @return a ResponseEntity with a BAD_REQUEST status and a message indicating the missing parameter
     */
	@ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        return new ResponseEntity<>("The parameter '" + name + "' is required", HttpStatus.BAD_REQUEST);
    }
	
	 /**
     * Handles MethodArgumentTypeMismatchExceptions.
     *
     * @param ex the exception
     * @return a ResponseEntity with a BAD_REQUEST status and a message indicating the incorrect parameter
     */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	            .body("Incorrect parameter: " + ex.getMessage());
	}

}
