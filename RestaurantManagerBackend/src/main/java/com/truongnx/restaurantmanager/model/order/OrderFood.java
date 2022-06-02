package com.truongnx.restaurantmanager.model.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.truongnx.restaurantmanager.model.Food;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "food_order")
public class OrderFood {

    @EmbeddedId
    @JsonIgnore
    private OrderFoodKey orderKey;

    @Column(name = "quantity",nullable = false)
    private int quantity;

    public OrderFood() {
        super();
    }

    public OrderFood(Food food, Order order, int quantity) {
        orderKey=new OrderFoodKey();
        orderKey.setFood(food);
        orderKey.setOrder(order);
        this.quantity = quantity;
    }

    @Transient
    public Food getFood() {
        return this.orderKey.getFood();
    }

    @Transient
    public Order getOrder() {
        return this.orderKey.getOrder();
    }

    @Transient
    public Long getTotalPrice() {
        return getFood().getPrice() * getQuantity();
    }

    public OrderFoodKey getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(OrderFoodKey orderKey) {
        this.orderKey = orderKey;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((orderKey == null) ? 0 : orderKey.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OrderFood other = (OrderFood) obj;
        if (orderKey == null) {
            if (other.orderKey != null) {
                return false;
            }
        } else if (!orderKey.equals(other.orderKey)) {
            return false;
        }

        return true;
    }
  
}
