package com.example.productservice.service;

import com.example.productservice.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product saveProduct(Product product);
    Optional<Product> getProductById(Long id);
    List<Product> getAllProducts();
    Product updateProduct(Product product);
    void deleteProduct(Long id);
    List<Product> getProductsByPriceRange(Long minPrice, Long maxPrice);
}
