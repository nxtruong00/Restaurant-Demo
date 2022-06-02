package com.truongnx.restaurantmanager.model.order;

import com.truongnx.restaurantmanager.dto.OrderFoodDto;

import java.util.List;

public class OrderForm {
    private List<OrderFoodDto> foodOrders;

    public List<OrderFoodDto> getFoodOrders() {
        return foodOrders;
    }

    public void setFoodOrders(List<OrderFoodDto> foodOrders) {
        this.foodOrders = foodOrders;
    }
}
