package com.truongnx.restaurantmanager.dto;

import com.truongnx.restaurantmanager.model.Food;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodListDto {
    private List<Food>listFood;
    private int totalPage;
}
