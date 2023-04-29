package com.example.orderservice.model;

import com.example.orderservice.entity.Order;
import lombok.*;
import org.springframework.lang.NonNull; // Add this import statement

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    @NonNull
    private long productId;
    @NonNull
    private long totalAmount;
    @NonNull
    private long quantity;
    public Order toOrder() {
        return Order.builder()
                .amount(this.totalAmount)
                .orderStatus("CREATED")
                .productId(this.productId)
                .orderDate(Instant.now())
                .quantity(this.quantity)
                .build();
    }
}

