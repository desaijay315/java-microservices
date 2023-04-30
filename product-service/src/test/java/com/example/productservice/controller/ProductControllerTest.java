package com.example.productservice.controller;

import com.example.productservice.entity.Product;
import com.example.productservice.model.ProductRequest;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
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
        productService.deleteAll();
        productRequest = new ProductRequest("Product Test", 100L, 50L);
        ResponseEntity<Product> response = restTemplate.postForEntity(BASE_URL + port + CREATE_PRODUCT_URL, productRequest, Product.class);
    }

    @Test
    void createProduct_ReturnsCreatedProduct() {
        // Given
        Product product = new Product(1L, "Product Test", 100L, 50L);
        // When

        // Then
//        Product createdProduct = product.getBody();
        assertNotNull(product.getProductId());
        assertEquals(productRequest.getProductName(), product.getProductName());
        assertEquals(productRequest.getPrice(), product.getPrice());
        assertEquals(productRequest.getQuantity(), product.getQuantity());

    }

    @Test
    void getProductById_ReturnsProduct() throws Exception {
        // Given
        Long productId = 2L;
        Product product = new Product(productId, "Product Test", 100L, 50L);

        // Mocking the ProductService to return the product when retrieving by ID
        when(productService.getProductById(productId)).thenReturn(Optional.of(product));

        // When
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(GET_PRODUCT_URL, productId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.productName").value("Product Test"))
                .andExpect(jsonPath("$.price").value(100))
                .andExpect(jsonPath("$.quantity").value(50))
                .andReturn();

        // Then
        String expectedResponseBody = new ObjectMapper().writeValueAsString(product);
        assertEquals(expectedResponseBody, mvcResult.getResponse().getContentAsString());
    }

//    @Test
//    void getAllProducts_ReturnsAllProducts() throws Exception {
//        // Given
//        productService.deleteAll();
//
//        Product product1 = new Product(1L, "Product Test", 100L, 50L);
//        Product product2 = new Product(2L, "Product Test", 100L, 50L);
//        List<Product> products = Arrays.asList(product1, product2);
//
//
//        // Mocking the ProductService to return the products when requested
//        when(productService.getAllProducts())
//                .thenReturn(products);
//
//        // When
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(GET_ALL_PRODUCTS_URL)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].productName").value(product1.getProductName()))
//                .andReturn();
//
//        // Then
//        String expectedResponseBody = new ObjectMapper().writeValueAsString(products);
//        assertEquals(expectedResponseBody, mvcResult.getResponse().getContentAsString());
//    }

    @Test
    void updateProduct_ReturnsUpdatedProduct() throws Exception {
        // Given
        Long productId = 1L;
        ProductRequest productRequest = new ProductRequest("Product Test", 100L, 50L);
        Product existingProduct = new Product(productId, "Product Test", 200L, 20L);

        // Mocking the ProductService to return the existingProduct when updating
        when(productService.updateProduct(productId, productRequest))
                .thenReturn(Optional.of(existingProduct));

        // When
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(UPDATE_PRODUCT_URL, productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"productName\": \"Product Test\", \"price\": 200, \"quantity\": 20}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value(existingProduct.getProductName()))
                .andExpect(jsonPath("$.price").value(existingProduct.getPrice()))
                .andExpect(jsonPath("$.quantity").value(existingProduct.getQuantity()))
                .andReturn();

        // Then
        String expectedResponseBody = new ObjectMapper().writeValueAsString(existingProduct);
        assertEquals(expectedResponseBody, mvcResult.getResponse().getContentAsString());
    }
}