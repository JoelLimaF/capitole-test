package com.capitole.pricingservice.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitole.pricingservice.domain.application.dto.PriceResponseDTO;
import com.capitole.pricingservice.domain.repository.PriceRepository;

@Service
public class PriceService {

    private final PriceRepository priceRepository;

    @Autowired
    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }
    
    public PriceResponseDTO getPrice(LocalDateTime applicationDate, Long productId, Long brandId) {

        return null;
    }    
    
}
