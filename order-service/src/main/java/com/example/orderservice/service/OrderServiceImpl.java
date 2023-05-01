package com.example.orderservice.service;

import com.example.orderservice.entity.Order;
import com.example.orderservice.exception.CustomException;
import com.example.orderservice.external.client.ProductService;
import com.example.orderservice.model.OrderRequest;
import com.example.orderservice.model.OrderResponse;
import com.example.orderservice.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Use the Feign client for ProductService
    @Autowired
    private ProductService productService;

    @Override
    public long placeOrder(OrderRequest orderRequest) {
        // Fetch the product details from ProductService using the Feign client
        log.info("Placing Order Request: {}", orderRequest);

        productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());

        // Create a new Order object
        log.info("Creating Order with Status CREATED");
        Order order = Order.builder()
                .amount(orderRequest.getTotalAmount())
                .orderStatus("CREATED")
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();

        order = orderRepository.save(order);

        // Save the order to the repository
        log.info("Order placed successfully with Order Id: {}", order.getId());
        return order.getId();
    }

    @Override
    public List<Order> getAllOrders() {
        // Fetch all orders from the repository
        return orderRepository.findAll();
    }
}
