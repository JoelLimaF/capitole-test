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

@Data
@Entity
@NoArgsConstructor
@Table(name = "Product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	@Size(max = 25, message = "Name can have a maximum of 25 characters")
	@Column(name = "name")
    private String name;

	@NonNull
	@Size(max = 150, message = "Description can have a maximum of 150 characters")
	@Column(name = "description")
    private String description;
}
