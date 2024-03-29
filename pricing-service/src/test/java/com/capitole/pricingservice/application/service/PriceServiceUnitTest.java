package com.capitole.pricingservice.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.when;

import java.lang.reflect.Field;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capitole.pricingservice.application.dto.PriceResponseDTO;
import com.capitole.pricingservice.application.exception.PriceNotFoundException;
import com.capitole.pricingservice.domain.entity.Brand;
import com.capitole.pricingservice.domain.entity.Price;
import com.capitole.pricingservice.domain.entity.Product;
import com.capitole.pricingservice.domain.repository.PriceRepository;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PriceServiceUnitTest {

    @Autowired
    private PriceService priceService;
    
    @MockBean
    private PriceRepository priceRepository;

    
    @Test
    public void testGetPriceWithMultipleSamePriority() {
        LocalDateTime applicationDate = LocalDateTime.parse("2022-06-16T21:00:00");
        Long productId = 35455L;
        Long brandId = 1L;
        
        Product product = new Product();
        product.setId(35455L);
        product.setName("test");
        product.setDescription("test");
        
        Brand brand = new Brand();
        product.setId(1L);
        product.setName("test");

        // Crear varios precios con diferentes prioridades y fechas de finalización
        Price price1 = new Price();
        price1.setPriority(1);
        price1.setStartDate(LocalDateTime.parse("2022-06-16T15:00:00"));
        price1.setEndDate(LocalDateTime.parse("2022-06-16T22:00:00"));
        price1.setPriceList(1L);
        price1.setProduct(product);
        price1.setBrand(brand);

        Price price2 = new Price();
        price2.setPriority(2);
        price2.setStartDate(LocalDateTime.parse("2022-06-16T15:00:00"));
        price2.setEndDate(LocalDateTime.parse("2022-06-16T23:00:00"));
        price2.setPriceList(2L);
        price2.setProduct(product);
        price2.setBrand(brand);

        Price price3 = new Price();
        price3.setPriority(2);
        price3.setStartDate(LocalDateTime.parse("2022-06-16T15:00:00"));
        price3.setEndDate(LocalDateTime.parse("2022-06-16T21:00:00"));
        price3.setPriceList(3L);
        price3.setProduct(product);
        price3.setBrand(brand);

        List<Price> prices = Arrays.asList(price1, price2, price3);

        // Configurar el comportamiento del mock
        when(priceRepository.findPricesByDateProductAndBrand(applicationDate, productId, brandId)).thenReturn(prices);

        // Llamar al método que se está probando
        PriceResponseDTO result = priceService.getPrice(applicationDate, productId, brandId);

        // Verificar que se seleccionó el precio correcto
        assertEquals(result.getPriceList(), 2L);
    }
    
    @Test
    public void testGetPriceNotFoundException() {
 
        LocalDateTime applicationDate = LocalDateTime.parse("2022-06-16T21:00:00");
        Long productId = 35455L;
        Long brandId = 1L;

        // Esperar que se lance la excepción PriceNotFoundException
        Exception exception = assertThrows(PriceNotFoundException.class, () -> {
            priceService.getPrice(applicationDate, productId, brandId);
        });

        // Verificar el mensaje de la excepción
        String expectedMessage = "Currently, the product " + productId + " from the chain " + brandId + " does not have a price available";
        String exceptionMessage = exception.getMessage();

        assertEquals(expectedMessage, exceptionMessage);
    } 

    @Test
    public void testDependencyInjection() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        // Verificar que el servicio no es nulo
        assertNotNull(priceService);

        // Verificar que el repositorio no es nulo
        assertNotNull(priceRepository);

        // Obtener el campo 'priceRepository' del servicio
        Field field = PriceService.class.getDeclaredField("priceRepository");
        field.setAccessible(true);

        // Verificar que el valor del campo 'priceRepository' en el servicio es el mismo que el repositorio inyectado
        assertEquals(priceRepository, field.get(priceService));
    }
    
    
}
