package com.example.orderservice.service;

import com.example.orderservice.entity.Order;
import com.example.orderservice.model.OrderRequest;
import com.example.orderservice.model.OrderResponse;

import java.util.List;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);
    List<Order> getAllOrders();
}