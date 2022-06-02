package com.truongnx.restaurantmanager.service.impl;

import com.truongnx.restaurantmanager.model.order.OrderFood;
import com.truongnx.restaurantmanager.repository.OrderFoodRepository;
import com.truongnx.restaurantmanager.service.OrderFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderFoodServiceImpl implements OrderFoodService {

    @Autowired
    OrderFoodRepository orderFoodRepository;

    @Override
    public OrderFood createOrderFood(OrderFood orderFood) {
        return this.orderFoodRepository.save(orderFood);
    }
}
