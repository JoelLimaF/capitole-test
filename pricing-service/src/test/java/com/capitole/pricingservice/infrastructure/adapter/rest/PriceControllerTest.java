package com.capitole.pricingservice.infrastructure.adapter.rest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.capitole.pricingservice.application.service.PriceService;


@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Mock
    private PriceService priceServiceMock;

    @Test
    public void testConstructor() {
        PriceController priceController = new PriceController(priceServiceMock);
        assertNotNull(priceController);
    }
    
    @Test
    public void getPriceTest1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/price")
                .param("requestDate", "2020-06-14T10:00:00")
                .param("productId", "35455")
                .param("brandId", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-14T00:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("$.finalPrice").value(35.50));
    }
    
    @Test
    public void getPriceTest2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/price")
                .param("requestDate", "2020-06-14T16:00:00")
                .param("productId", "35455")
                .param("brandId", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.startDate").value("2020-06-14T15:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-06-14T18:30:00"))
                .andExpect(jsonPath("$.finalPrice").value(25.45));
    }
    
    @Test
    public void getPriceTest3() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/price")
                .param("requestDate", "2020-06-14T21:00:00")
                .param("productId", "35455")
                .param("brandId", "1")
                .accept(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$.productId").value(35455))
		        .andExpect(jsonPath("$.brandId").value(1))
		        .andExpect(jsonPath("$.priceList").value(1))
		        .andExpect(jsonPath("$.startDate").value("2020-06-14T00:00:00"))
		        .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:59"))
		        .andExpect(jsonPath("$.finalPrice").value(35.50));
    }
    
    @Test
    public void getPriceTest4() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/price")
                .param("requestDate", "2020-06-15T10:00:00")
                .param("productId", "35455")
                .param("brandId", "1")
                .accept(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$.productId").value(35455))
		        .andExpect(jsonPath("$.brandId").value(1))
		        .andExpect(jsonPath("$.priceList").value(3))
		        .andExpect(jsonPath("$.startDate").value("2020-06-15T00:00:00"))
		        .andExpect(jsonPath("$.endDate").value("2020-06-15T11:00:00"))
		        .andExpect(jsonPath("$.finalPrice").value(30.50));
    }
    
    @Test
    public void getPriceTest5() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/price")
                .param("requestDate", "2020-06-16T21:00:00")
                .param("productId", "35455")
                .param("brandId", "1")
                .accept(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$.productId").value(35455))
		        .andExpect(jsonPath("$.brandId").value(1))
		        .andExpect(jsonPath("$.priceList").value(4))
		        .andExpect(jsonPath("$.startDate").value("2020-06-15T16:00:00"))
		        .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:59"))
		        .andExpect(jsonPath("$.finalPrice").value(38.95));
    }
    
    
    @Test
    public void getPriceTestNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/price")
                .param("requestDate", "2022-06-14T10:00:00")
                .param("productId", "35455")
                .param("brandId", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    
    @Test
    public void getPriceTestNotFoundCustomMesage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/price")
                .param("requestDate", "2022-06-14T10:00:00")
                .param("productId", "35455")
                .param("brandId", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Currently, the product 35455 from the chain 1 does not have a price available")));
    }
    
    
    @Test
    public void getPriceTestMissingRequestDate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/price")
                .param("productId", "35455")
                .param("brandId", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("The parameter 'requestDate' is required")));
    }
    
    @Test
    public void getPriceTestMissingProductId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/price")
                .param("requestDate", "2020-06-14T10:00:00")
                .param("brandId", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("The parameter 'productId' is required")));
    }
    
    @Test
    public void getPriceTestMissingBrandId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/price")
                .param("requestDate", "2020-06-14T10:00:00")
                .param("productId", "35455")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("The parameter 'brandId' is required")));
    }
    
    @Test
    public void getPriceTestInvalidDateFormat() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/price")
                .param("requestDate", "14-06-2020T10:00:00")
                .param("productId", "35455")
                .param("brandId", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(startsWith("Incorrect parameter: ")));
    }
    
    @Test
    public void getPriceTestInvalidProductId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/price")
                .param("requestDate", "2020-06-14T10:00:00")
                .param("productId", "99999")
                .param("brandId", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    
    @Test
    public void getPriceTestInvalidBrandId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/price")
                .param("requestDate", "2020-06-14T10:00:00")
                .param("productId", "35455")
                .param("brandId", "2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    
    @Test
    public void getPriceTestInvalidProductIdFormat() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/price")
                .param("requestDate", "2020-06-14T10:00:00")
                .param("productId", "invalidProductId")
                .param("brandId", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(startsWith("Incorrect parameter:")));
    }
    
    @Test
    public void getPriceTestInvalidBrandIdFormat() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/price")
                .param("requestDate", "2020-06-14T10:00:00")
                .param("productId", "35455")
                .param("brandId", "invalidBrandId")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(startsWith("Incorrect parameter:")));
    }

    
}
