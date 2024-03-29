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
 * Represents a Currency entity in the database.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "Currency")
public class Currency {

	/**
     * The unique identifier of the currency.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The code ISO of the currency. This field is required and must be exactly 3 characters long.
     */
    @NonNull
    @Size(min = 3, max = 3, message = "Code must be 3 characters")
    @Column(name = "code", unique = true)
    private String code;

    /**
     * The name of the currency. This field is required and can have a maximum of 25 characters.
     */
    @NonNull
    @Size(max = 25, message = "Name can have a maximum of 25 characters")
    @Column(name = "name")
    private String name;
}
