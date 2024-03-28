package com.capitole.pricingservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capitole.pricingservice.domain.entity.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long>{

}
