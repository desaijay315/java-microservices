package com.example.productservice.model;

import com.example.productservice.entity.Product;
import lombok.*;

import javax.validation.constraints.*;


@Data
@AllArgsConstructor
@Builder
public class ProductRequest {

    @NotNull(message = "Product name shouldn't be null")
    private String productName;

    @NotNull(message = "Price is required")
    private Long price;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private Long quantity;

    public Product toProduct() {
        return Product.builder()
                .productName(this.productName)
                .price(this.price)
                .quantity(this.quantity)
                .build();
    }
}
