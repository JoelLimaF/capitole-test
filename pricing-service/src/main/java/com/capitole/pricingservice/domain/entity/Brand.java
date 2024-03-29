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
 * Represents a Brand entity in the database.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "Brand")
public class Brand {

	/**
     * The unique identifier of the brand.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	 /**
     * The name of the brand. This field is required and can have a maximum of 25 characters.
     */
	@NonNull
	@Size(max = 25, message = "Name can have a maximum of 25 characters")
	@Column(name = "name")
    private String name;
}
