package com.capitole.pricingservice.domain.application.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PriceResponseDTO {
	
	private Long productId; // identificador de producto
	private Long brandId; // identificador de cadena
	private Long priceList; // tarifa a aplicar
	private LocalDateTime startDate; // fechas de aplicación
    private LocalDateTime endDate; // fechas de aplicación
    private Double finalPrice; // precio final a aplicar

}
