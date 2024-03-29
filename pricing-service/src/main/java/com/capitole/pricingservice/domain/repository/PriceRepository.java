package com.capitole.pricingservice.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capitole.pricingservice.domain.entity.Price;

/**
 * Repository interface for managing Price entities.
 */
@Repository
public interface PriceRepository extends JpaRepository<Price, Long>{

	/**
     * Finds prices by application date, product ID, and brand ID.
     *
     * @param applicationDate the date for which to find the prices
     * @param productId the ID of the product
     * @param brandId the ID of the brand
     * @return a list of prices that match the given parameters
     */
	@Query("SELECT p FROM Price p " +
	        "WHERE :applicationDate BETWEEN p.startDate AND p.endDate " +
	        "AND p.product.id = :productId " +
	        "AND p.brand.id = :brandId ")
	List<Price> findPricesByDateProductAndBrand(@Param("applicationDate") LocalDateTime applicationDate,
	                        @Param("productId") Long productId,
	                        @Param("brandId") Long brandId);

}
