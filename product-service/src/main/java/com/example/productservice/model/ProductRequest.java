package com.example.productservice.model;

import com.example.productservice.entity.Product;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
public class ProductRequest {
    @NonNull
    @NotBlank(message = "Product name is required")
    @NotEmpty(message = "Product name is required")
    private String productName;

    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be greater than 0")
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
