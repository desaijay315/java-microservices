package com.example.orderservice.service;

import com.example.orderservice.entity.Order;
import com.example.orderservice.external.client.ProductService;
import com.example.orderservice.model.OrderRequest;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Use the Feign client for ProductService
    @Autowired
    private ProductService productService;

    @Override
    public Order placeOrder(OrderRequest orderRequest) {
        // Fetch the product details from ProductService using the Feign client
        productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());

        // Create a new Order object
        Order order = orderRequest.toOrder();

        // Save the order to the repository
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderDetails(Long orderId) {
        // Fetch the order details from the repository using the provided orderId
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public List<Order> getAllOrders() {
        // Fetch all orders from the repository
        return orderRepository.findAll();
    }
}
