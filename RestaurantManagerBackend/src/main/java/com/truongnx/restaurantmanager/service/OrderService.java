package com.truongnx.restaurantmanager.service;

import com.truongnx.restaurantmanager.model.order.Order;

import java.util.List;

public interface OrderService {
    public List<Order> getAllOrders();

    public Order create(Order order);

    public void update(Order order);
}
