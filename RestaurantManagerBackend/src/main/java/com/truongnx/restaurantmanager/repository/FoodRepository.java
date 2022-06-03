package com.truongnx.restaurantmanager.repository;

import com.truongnx.restaurantmanager.model.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food,String> {
    @Query("SELECT f FROM Food f WHERE f.name LIKE %:keyWord%")
    public Page<Food> searchFoodByKeyWord(@Param("keyWord") String keyWord, Pageable pageable);

    @Query("SELECT COUNT(f.id) FROM Food f WHERE f.name LIKE %:keyWord%")
    public int countFoodByKeyWord(@Param("keyWord") String keyWord);
}
