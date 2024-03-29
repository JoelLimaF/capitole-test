package com.capitole.pricingservice.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.capitole.pricingservice.domain.entity.Price;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class PriceResponseDTO {
	
	private Long productId; // identificador de producto
	private Long brandId; // identificador de cadena
	private Long priceList; // tarifa a aplicar
	private LocalDateTime startDate; // fechas de aplicación
    private LocalDateTime endDate; // fechas de aplicación
    private BigDecimal finalPrice; // precio final a aplicar
    
    public PriceResponseDTO(Price price) {
        this.productId = price.getProduct().getId();
        this.brandId = price.getBrand().getId();
        this.priceList = price.getPriceList();
        this.startDate = price.getStartDate();
        this.endDate = price.getEndDate();
        this.finalPrice = price.getPrice();
    }

}
