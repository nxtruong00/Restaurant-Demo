package com.truongnx.restaurantmanager.service;

import com.truongnx.restaurantmanager.model.order.OrderFood;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface OrderFoodService {

   public OrderFood createOrderFood(@NotNull(message = "The products for order cannot be null.") @Valid OrderFood orderFood);
}
