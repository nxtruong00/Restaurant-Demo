package com.truongnx.restaurantmanager.dto;

import com.truongnx.restaurantmanager.model.Food;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderFoodDto {

    private Food food;
    private int quantity;
}
