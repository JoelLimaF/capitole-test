package com.capitole.pricingservice.infrastructure.adapter.rest;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capitole.pricingservice.application.dto.PriceResponseDTO;
import com.capitole.pricingservice.application.service.PriceService;

@RestController
public class PriceController {

	private final PriceService priceService;

	/**
     * Constructs a new PriceController with the given PriceService.
     *
     * @param priceService the service to use for price management
     */
    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }
    
    /**
     * Endpoint to get the price of a product at a specific date for a specific brand.
     *
     * @param requestDate the date for which to get the price
     * @param productId the ID of the product
     * @param brandId the ID of the brand
     * @return a ResponseEntity containing the price of the product for the given brand at the given date
     */
    @GetMapping("/price")
    public ResponseEntity<PriceResponseDTO> getPrice(
    		@NonNull @RequestParam("requestDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime requestDate,
            @NonNull @RequestParam("productId") final Long productId,
            @NonNull @RequestParam("brandId") final Long brandId
    ) {
        return ResponseEntity.ok(priceService.getPrice(requestDate, productId, brandId));
    }
    
}
