package com.capitole.pricingservice.domain.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PriceNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2112276267008376292L;

	public PriceNotFoundException(String message) {
        super(message);
    }
}
