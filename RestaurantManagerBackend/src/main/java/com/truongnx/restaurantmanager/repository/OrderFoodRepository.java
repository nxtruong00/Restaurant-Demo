package com.truongnx.restaurantmanager.repository;

import com.truongnx.restaurantmanager.model.order.OrderFood;
import com.truongnx.restaurantmanager.model.order.OrderFoodKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderFoodRepository extends JpaRepository<OrderFood, OrderFoodKey> {
}
