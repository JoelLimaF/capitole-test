package com.capitole.pricingservice.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDTO {
    private Long productId;
    private String name;
    private String description;
}
