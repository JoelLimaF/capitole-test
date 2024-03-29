package com.capitole.pricingservice.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitole.pricingservice.domain.application.dto.PriceResponseDTO;
import com.capitole.pricingservice.domain.entity.Price;
import com.capitole.pricingservice.domain.exception.PriceNotFoundException;
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
            throw new PriceNotFoundException("No price found for the given date, product ID and brand ID");
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
    	
    	
    	Long productId = selectedPrice.getProduct().getId();
    	Long brandId = selectedPrice.getBrand().getId();
    	Long priceList = selectedPrice.getPriceList();
    	LocalDateTime startDate = selectedPrice.getStartDate();
    	LocalDateTime endDate = selectedPrice.getEndDate();
    	BigDecimal finalPrice = selectedPrice.getPrice();
    	
    	
    	return new PriceResponseDTO(productId, brandId, priceList, startDate, endDate, finalPrice);
    }
       
}
