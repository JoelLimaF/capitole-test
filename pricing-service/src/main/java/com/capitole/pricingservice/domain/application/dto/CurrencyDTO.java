package com.capitole.pricingservice.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CurrencyDTO {
    private Long id;
    private String code;
    private String name;
}
