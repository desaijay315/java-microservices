package com.example.productservice.service;

import com.example.productservice.entity.Product;
import com.example.productservice.model.ProductRequest;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product saveProduct(Product product);
    Optional<Product> getProductById(Long id);
    List<Product> getAllProducts();
    Optional<Product> updateProduct(Long productId, ProductRequest productRequest);
    void deleteProduct(Long id);
    List<Product> getProductsByPriceRange(Long minPrice, Long maxPrice);
    void reduceQuantity(long productId, long quantity);
}
