package com.example.productservice.model;

import com.example.productservice.entity.Product;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    @NonNull
    private String productName;
    @NonNull
    private Long price;
    @NonNull
    private Long quantity;

    public Product toProduct() {
        return Product.builder()
                .productName(this.productName)
                .price(this.price)
                .quantity(this.quantity)
                .build();
    }
}
