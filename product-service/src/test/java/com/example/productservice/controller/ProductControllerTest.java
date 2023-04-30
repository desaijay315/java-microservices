package com.example.productservice.controller;

import com.example.productservice.entity.Product;
import com.example.productservice.model.ProductRequest;
import com.example.productservice.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @LocalServerPort
    private int port;

    private static final String BASE_URL = "http://localhost:";
    private static final String CREATE_PRODUCT_URL = "/product/create-product";
    private static final String GET_PRODUCT_URL = "/product/{id}";
    private static final String GET_ALL_PRODUCTS_URL = "/product";
    private static final String UPDATE_PRODUCT_URL = "/product/{id}";
    private static final String DELETE_PRODUCT_URL = "/product/{id}";
    private static final String GET_PRODUCTS_BY_PRICE_RANGE_URL = "/product/price-range";
    private static final String REDUCE_PRODUCT_QUANTITY_URL = "/product/reduceQuantity/{id}";

    private ProductRequest productRequest;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        productRequest = new ProductRequest("Product Test", 100L, 50L);
    }

    @Test
    void createProduct_ReturnsCreatedProduct() {
        // Given
        Product product = new Product(1L, "Product Test", 100L, 50L);
        // When
        ResponseEntity<Product> response = restTemplate.postForEntity(BASE_URL + port + CREATE_PRODUCT_URL, productRequest, Product.class);

// Then
        Product createdProduct = response.getBody();
        assertNotNull(createdProduct.getProductId());
        assertEquals(productRequest.getProductName(), createdProduct.getProductName());
        assertEquals(productRequest.getPrice(), createdProduct.getPrice());
        assertEquals(productRequest.getQuantity(), createdProduct.getQuantity());

    }

    @Test
    void getProductById_ReturnsProduct() throws Exception {
        // Given
        Long productId = 1L;
        Product product = new Product(productId, "Test Product", 100L, 10L);

        // Mocking the ProductService to return the product when retrieving by ID
        when(productService.getProductById(productId)).thenReturn(Optional.of(product));

        // When
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(GET_PRODUCT_URL, productId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.productName").value("Test Product"))
                .andExpect(jsonPath("$.price").value(100))
                .andExpect(jsonPath("$.quantity").value(10))
                .andReturn();

        // Then
        String expectedResponseBody = new ObjectMapper().writeValueAsString(product);
        assertEquals(expectedResponseBody, mvcResult.getResponse().getContentAsString());
    }




}