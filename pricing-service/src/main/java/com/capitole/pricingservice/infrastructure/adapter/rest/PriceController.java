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

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }
    
    @GetMapping("/price")
    public ResponseEntity<PriceResponseDTO> getPrice(
    		@NonNull @RequestParam("requestDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime requestDate,
            @NonNull @RequestParam("productId") final Long productId,
            @NonNull @RequestParam("brandId") final Long brandId
    ) {
        return ResponseEntity.ok(priceService.getPrice(requestDate, productId, brandId));
    }
    
}
