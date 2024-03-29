package com.capitole.pricingservice.application.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Exception thrown when a price is not found.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PriceNotFoundException extends RuntimeException {

	/**
     * Serial version UID for serialization.
     */
	private static final long serialVersionUID = 2112276267008376292L;

	/**
     * Constructs a new PriceNotFoundException with the given message.
     *
     * @param message the message to use for the exception
     */
	public PriceNotFoundException(String message) {
        super(message);
    }
}
