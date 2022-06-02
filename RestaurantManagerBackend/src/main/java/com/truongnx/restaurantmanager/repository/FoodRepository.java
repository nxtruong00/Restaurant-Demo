package com.truongnx.restaurantmanager.repository;

import com.truongnx.restaurantmanager.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food,String> {
}
