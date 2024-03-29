package com.capitole.pricingservice.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capitole.pricingservice.domain.entity.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long>{

	@Query("SELECT p FROM Price p " +
	        "WHERE :applicationDate BETWEEN p.startDate AND p.endDate " +
	        "AND p.product.id = :productId " +
	        "AND p.brand.id = :brandId ")
	List<Price> findPricesByDateProductAndBrand(@Param("applicationDate") LocalDateTime applicationDate,
	                        @Param("productId") Long productId,
	                        @Param("brandId") Long brandId);

}
