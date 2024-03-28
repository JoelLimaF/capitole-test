package com.capitole.pricingservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capitole.pricingservice.domain.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
