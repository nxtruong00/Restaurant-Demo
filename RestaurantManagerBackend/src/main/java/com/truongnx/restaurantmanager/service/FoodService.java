package com.truongnx.restaurantmanager.service;

import com.truongnx.restaurantmanager.exception.ResourceNotFoundException;
import com.truongnx.restaurantmanager.model.Food;
import com.truongnx.restaurantmanager.model.FoodForm;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.List;

public interface FoodService {

    public Food addFood(Food food);

    public Food updateFood(Food food);

    public void deleteFood(String foodId);

    public List<Food>getFoods(int pageNo, int pageSize);

    public int getTotalFoods();

    public Food getFoodById(String foodId) throws ResourceNotFoundException;

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException;
}
