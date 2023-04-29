package com.example.orderservice.service;

import com.example.orderservice.entity.Order;
import com.example.orderservice.model.OrderRequest;

import java.util.List;

public interface OrderService {
    Order placeOrder(OrderRequest order);
    Order getOrderDetails(Long orderId);
    List<Order> getAllOrders();
}