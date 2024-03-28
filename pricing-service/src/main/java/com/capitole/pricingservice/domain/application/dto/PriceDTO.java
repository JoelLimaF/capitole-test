package com.capitole.pricingservice.domain.application.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PriceDTO {
    private Long priceId;
    private BrandDTO brand;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long priceList;
    private ProductDTO product;
    private Integer priority;
    private Double price;
    private CurrencyDTO currency;
}
