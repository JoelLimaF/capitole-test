package com.capitole.pricingservice.domain.entity;

import org.springframework.lang.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a Product entity in the database.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "Product")
public class Product {

	 /**
     * The unique identifier of the product.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
     * The name of the product. This field is required and can have a maximum of 25 characters.
     */
	@NonNull
	@Size(max = 25, message = "Name can have a maximum of 25 characters")
	@Column(name = "name")
    private String name;

	/**
     * The description of the product. This field is required and can have a maximum of 150 characters.
     */
	@NonNull
	@Size(max = 150, message = "Description can have a maximum of 150 characters")
	@Column(name = "description")
    private String description;
}
