package com.capitole.pricingservice.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.capitole.pricingservice.domain.entity.Price;

import lombok.Getter;

/**
 * Data Transfer Object for sending price information in responses.
 */
@Getter
public class PriceResponseDTO {
	
    /**
     * The unique identifier of the product.
     */
	private Long productId;

    /**
     * The unique identifier of the brand.
     */
	private Long brandId;

    /**
     * The price list identifier.
     */
	private Long priceList;

    /**
     * The start date of the price validity.
     */
	private LocalDateTime startDate;

    /**
     * The end date of the price validity.
     */
    private LocalDateTime endDate;

    /**
     * The final price to apply.
     */
    private BigDecimal finalPrice;
    
    /**
     * Constructs a new PriceResponseDTO with the given Price.
     *
     * @param price the Price to use for creating the PriceResponseDTO
     */
    public PriceResponseDTO(Price price) {
        this.productId = price.getProduct().getId();
        this.brandId = price.getBrand().getId();
        this.priceList = price.getPriceList();
        this.startDate = price.getStartDate();
        this.endDate = price.getEndDate();
        this.finalPrice = price.getPrice();
    }
}

