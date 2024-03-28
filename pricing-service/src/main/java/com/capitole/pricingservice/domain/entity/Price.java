package com.capitole.pricingservice.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Price")
public class Price {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final Long priceId;
	
	@ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "brandId")
    private final Brand brand;

    private final LocalDateTime startDate;

    private final LocalDateTime endDate;

    private final Long priceList;
    
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private final Product product;

    private final Integer priority;

    private final BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private final Currency currency;

}
