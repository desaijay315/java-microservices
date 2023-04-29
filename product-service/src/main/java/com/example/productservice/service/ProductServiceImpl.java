package com.example.productservice.service;

import com.example.productservice.entity.Product;
import com.example.productservice.model.ProductRequest;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> updateProduct(Long productId, ProductRequest productRequest) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setProductName(productRequest.getProductName());
            product.setPrice(productRequest.getPrice());
            product.setQuantity(productRequest.getQuantity());
            Product updatedProduct = productRepository.save(product);
            return Optional.of(updatedProduct);
        } else {
            return Optional.empty();
        }
    }



    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found with id " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductsByPriceRange(Long minPrice, Long maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }
}
