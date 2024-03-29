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

@Service
public class PriceService {

    private final PriceRepository priceRepository;

    @Autowired
    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }
    
    public PriceResponseDTO getPrice(final LocalDateTime applicationDate, final Long productId, final Long brandId) {

    	List<Price> prices = priceRepository.findPricesByDateProductAndBrand(applicationDate, productId, brandId);
    	
    	if (prices.isEmpty()) {
            throw new PriceNotFoundException("Currently, the product " + productId + " from the chain " + brandId + " does not have a price available");
        }
    	
        return selectFinalPrice(prices);
    }    
    
    private PriceResponseDTO selectFinalPrice(List<Price> prices) {
    	
    	// Selecciona el precio con la prioridad más alta. En caso de existir 2 o más fechas con la misma prioridad "máxima",
    	// seleccionará el precio con la fecha "endDate" más longeva.
    	Price selectedPrice = prices.stream()
    		    .max(Comparator.comparingInt(Price::getPriority)
    		        .thenComparing(Comparator.comparing(Price::getEndDate).reversed()))
    		    .orElseThrow(() -> new IllegalStateException("Unexpected error when selecting price")); 	
    	
    	return new PriceResponseDTO(selectedPrice);
    }
       
}
