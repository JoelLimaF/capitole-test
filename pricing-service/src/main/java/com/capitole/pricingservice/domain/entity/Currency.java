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
@Table(name = "Currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Size(min = 3, max = 3, message = "Code must be 3 characters")
    @Column(name = "code", unique = true)
    private String code;

    @NonNull
    @Size(max = 25, message = "Name can have a maximum of 25 characters")
    @Column(name = "name")
    private String name;
}
