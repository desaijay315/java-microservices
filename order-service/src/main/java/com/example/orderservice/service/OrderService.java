package com.example.orderservice.service;

import com.example.orderservice.entity.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder(Long productId, Long quantity);
    Order getOrderDetails(Long orderId);
    List<Order> getAllOrders();
}