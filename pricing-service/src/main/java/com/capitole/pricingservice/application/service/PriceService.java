package com.capitole.pricingservice.application.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitole.pricingservice.application.dto.PriceResponseDTO;
import com.capitole.pricingservice.application.exception.PriceNotFoundException;
import com.capitole.pricingservice.domain.entity.Price;
import com.capitole.pricingservice.domain.repository.PriceRepository;

/**
 * Service class for managing product prices.
 */
@Service
public class PriceService {

    private final PriceRepository priceRepository;

    /**
     * Constructs a new PriceService with the given PriceRepository.
     *
     * @param priceRepository the repository to use for price data access
     */
    @Autowired
    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }
    
    /**
     * Gets the price of a product at a specific date for a specific brand.
     *
     * @param applicationDate the date for which to get the price
     * @param productId the ID of the product
     * @param brandId the ID of the brand
     * @return the price of the product for the given brand at the given date
     * @throws PriceNotFoundException if no prices are available for the given product and brand at the application date
     */
    public PriceResponseDTO getPrice(
    		final LocalDateTime applicationDate, 
    		final Long productId, 
    		final Long brandId) {

    	List<Price> prices = priceRepository.findPricesByDateProductAndBrand(applicationDate, productId, brandId);
    	
    	if (prices.isEmpty()) {
            throw new PriceNotFoundException("Currently, the product " + productId + " from the chain " + brandId + " does not have a price available");
        }
    	
        return selectFinalPrice(prices);
    }    
    
    /**
     * Selects the price with the highest priority. In case there are 2 or more dates with the same "maximum" priority,
     * it will select the price with the longest "endDate".
     *
     * @param prices the list of prices from which the final price will be selected
     * @return the selected final price, wrapped in a PriceResponseDTO object
     * @throws IllegalStateException if an unexpected error occurs when selecting the price
     */

    private PriceResponseDTO selectFinalPrice(List<Price> prices) {
    	
    	Price selectedPrice = prices.stream()
    		    .max(Comparator.comparingInt(Price::getPriority)
    		        .thenComparing(Comparator.comparing(Price::getEndDate)))
    		    .orElseThrow(() -> new IllegalStateException("Unexpected error when selecting price")); 	
    	
    	return new PriceResponseDTO(selectedPrice);
    }
       
}
