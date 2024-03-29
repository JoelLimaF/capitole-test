package com.capitole.pricingservice.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capitole.pricingservice.application.dto.PriceResponseDTO;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PriceServiceIntegrationTest {

    @Autowired
    private PriceService priceService;
    
    @Test
    public void testGetPrice1() {
 
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14T10:00:00");
        Long productId = 35455L;
        Long brandId = 1L;

        PriceResponseDTO result = priceService.getPrice(applicationDate, productId, brandId);

        assertEquals(35455L, result.getProductId());
        assertEquals(1L, result.getBrandId());
        assertEquals(1L, result.getPriceList());
        assertEquals(LocalDateTime.parse("2020-06-14T00:00:00"), result.getStartDate());
        assertEquals(LocalDateTime.parse("2020-12-31T23:59:59"), result.getEndDate());
        assertEquals(new BigDecimal("35.50"), result.getFinalPrice());
    }
    
    @Test
    public void testGetPrice2() {
 
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14T16:00:00");
        Long productId = 35455L;
        Long brandId = 1L;

        PriceResponseDTO result = priceService.getPrice(applicationDate, productId, brandId);

        assertEquals(35455L, result.getProductId());
        assertEquals(1L, result.getBrandId());
        assertEquals(2L, result.getPriceList());
        assertEquals(LocalDateTime.parse("2020-06-14T15:00:00"), result.getStartDate());
        assertEquals(LocalDateTime.parse("2020-06-14T18:30:00"), result.getEndDate());
        assertEquals(new BigDecimal("25.45"), result.getFinalPrice());
    }
    
    @Test
    public void testGetPrice3() {
 
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14T21:00:00");
        Long productId = 35455L;
        Long brandId = 1L;

        // Llamar al método que se está probando
        PriceResponseDTO result = priceService.getPrice(applicationDate, productId, brandId);

        // Verificar el resultado
        assertEquals(35455L, result.getProductId());
        assertEquals(1L, result.getBrandId());
        assertEquals(1L, result.getPriceList());
        assertEquals(LocalDateTime.parse("2020-06-14T00:00:00"), result.getStartDate());
        assertEquals(LocalDateTime.parse("2020-12-31T23:59:59"), result.getEndDate());
        assertEquals(new BigDecimal("35.50"), result.getFinalPrice());
    }
    
    @Test
    public void testGetPrice4() {
 
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-15T10:00:00");
        Long productId = 35455L;
        Long brandId = 1L;

        // Llamar al método que se está probando
        PriceResponseDTO result = priceService.getPrice(applicationDate, productId, brandId);

        // Verificar el resultado
        assertEquals(35455L, result.getProductId());
        assertEquals(1L, result.getBrandId());
        assertEquals(3L, result.getPriceList());
        assertEquals(LocalDateTime.parse("2020-06-15T00:00:00"), result.getStartDate());
        assertEquals(LocalDateTime.parse("2020-06-15T11:00:00"), result.getEndDate());
        assertEquals(new BigDecimal("30.50"), result.getFinalPrice());
    }
    
    @Test
    public void testGetPrice5() {
 
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-16T21:00:00");
        Long productId = 35455L;
        Long brandId = 1L;

        // Llamar al método que se está probando
        PriceResponseDTO result = priceService.getPrice(applicationDate, productId, brandId);

        // Verificar el resultado
        assertEquals(35455L, result.getProductId());
        assertEquals(1L, result.getBrandId());
        assertEquals(4L, result.getPriceList());
        assertEquals(LocalDateTime.parse("2020-06-15T16:00:00"), result.getStartDate());
        assertEquals(LocalDateTime.parse("2020-12-31T23:59:59"), result.getEndDate());
        assertEquals(new BigDecimal("38.95"), result.getFinalPrice());
    }
    
    
    @Test
    public void testGetPriceLimitEndDate() {
 
        LocalDateTime applicationDate = LocalDateTime.parse("2020-12-31T23:59:59");
        Long productId = 35455L;
        Long brandId = 1L;

        // Llamar al método que se está probando
        PriceResponseDTO result = priceService.getPrice(applicationDate, productId, brandId);

        // Verificar el resultado
        assertEquals(35455L, result.getProductId());
        assertEquals(1L, result.getBrandId());
        assertEquals(4L, result.getPriceList());
        assertEquals(LocalDateTime.parse("2020-06-15T16:00:00"), result.getStartDate());
        assertEquals(LocalDateTime.parse("2020-12-31T23:59:59"), result.getEndDate());
        assertEquals(new BigDecimal("38.95"), result.getFinalPrice());
    }
    
    @Test
    public void testGetPriceLimitStartDate() {
 
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-15T16:00:00");
        Long productId = 35455L;
        Long brandId = 1L;

        // Llamar al método que se está probando
        PriceResponseDTO result = priceService.getPrice(applicationDate, productId, brandId);

        // Verificar el resultado
        assertEquals(35455L, result.getProductId());
        assertEquals(1L, result.getBrandId());
        assertEquals(4L, result.getPriceList());
        assertEquals(LocalDateTime.parse("2020-06-15T16:00:00"), result.getStartDate());
        assertEquals(LocalDateTime.parse("2020-12-31T23:59:59"), result.getEndDate());
        assertEquals(new BigDecimal("38.95"), result.getFinalPrice());
    }
    
}
