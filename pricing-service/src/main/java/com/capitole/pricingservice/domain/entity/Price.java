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

/**
 * Represents a Price entity in the database.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "Prices")
public class Price {
	
	/**
     * The unique identifier of the price.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
     * The brand associated with the price.
     */
	@NonNull
	@ManyToOne
    @JoinColumn(name = "brand", referencedColumnName = "id")
    private Brand brand;

	/**
     * The start date of the price validity.
     */
	@NonNull
	@Column(name = "start_date")
    private LocalDateTime startDate;

	 /**
     * The end date of the price validity.
     */
	@NonNull
	@Column(name = "end_date")
    private LocalDateTime endDate;

	/**
     * The price list identifier.
     */
	@NonNull
	@Column(name = "price_list")
    private Long priceList;
    
	/**
     * The product associated with the price.
     */
	@NonNull
    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "id")
    private Product product;

	/**
     * The priority of the price. In case of overlapping dates, the price with the highest priority is selected.
     */
	@NonNull
    @Column(name = "priority")
    private Integer priority;

	/**
     * The value of the price.
     */
	@NonNull
	@Digits(integer=10, fraction=2)
    @Column(name = "price")
    private BigDecimal price;

	/**
     * The currency of the price.
     */
	@NonNull
    @ManyToOne
    @JoinColumn(name = "currency", referencedColumnName = "code")
    private Currency currency;

}
