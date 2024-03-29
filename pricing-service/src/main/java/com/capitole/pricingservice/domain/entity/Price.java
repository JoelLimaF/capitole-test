package com.capitole.pricingservice.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.lang.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Prices")
public class Price {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@ManyToOne
    @JoinColumn(name = "brand", referencedColumnName = "id")
    private Brand brand;

	@NonNull
	@Column(name = "start_date")
    private LocalDateTime startDate;

	@NonNull
	@Column(name = "end_date")
    private LocalDateTime endDate;

	@NonNull
	@Column(name = "price_list")
    private Long priceList;
    
	@NonNull
    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "id")
    private Product product;

	@NonNull
    @Column(name = "priority")
    private Integer priority;

	@NonNull
	@Digits(integer=10, fraction=2)
    @Column(name = "price")
    private BigDecimal price;

	@NonNull
    @ManyToOne
    @JoinColumn(name = "currency", referencedColumnName = "code")
    private Currency currency;

}
