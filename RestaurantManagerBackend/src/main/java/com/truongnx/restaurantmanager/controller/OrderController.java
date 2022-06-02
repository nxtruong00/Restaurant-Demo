package com.truongnx.restaurantmanager.controller;

import com.truongnx.restaurantmanager.dto.OrderFoodDto;
import com.truongnx.restaurantmanager.model.Food;
import com.truongnx.restaurantmanager.model.order.Order;
import com.truongnx.restaurantmanager.model.order.OrderFood;
import com.truongnx.restaurantmanager.model.order.OrderForm;
import com.truongnx.restaurantmanager.service.FoodService;
import com.truongnx.restaurantmanager.service.OrderFoodService;
import com.truongnx.restaurantmanager.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController

@RequestMapping("/orders")
public class OrderController {
    @Autowired
    FoodService foodService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderFoodService orderFoodService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @NotNull List<Order> list() {
        return this.orderService.getAllOrders();
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderForm form) {
        List<OrderFoodDto> formDtos = form.getFoodOrders();
        Order order = new Order();

        order = this.orderService.create(order);
        order.setId(UUID.randomUUID().toString());
        List<OrderFood> orderFoods = new ArrayList<>();
        for (OrderFoodDto dto : formDtos) {
            Food newFood=foodService.getFoodById(dto.getFood().getId());
            orderFoods.add(orderFoodService.createOrderFood(new OrderFood(newFood,order, dto.getQuantity())));
        }

        order.setOrderFoods(orderFoods);

        this.orderService.update(order);

        String uri = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/orders/{id}")
                .buildAndExpand(order.getId())
                .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
    }

}
