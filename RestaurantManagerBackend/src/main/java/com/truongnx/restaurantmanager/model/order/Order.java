package com.truongnx.restaurantmanager.model.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.truongnx.restaurantmanager.model.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="orderFoods")
public class Order {
    @Id
    private String id;

    @Column(name = "create_at", updatable = false)
    @CreatedDate
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createAt;

    @Column(name = "total")
    private long total;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @OneToMany(mappedBy = "orderKey.order")
    @Valid
    private List<OrderFood> orderFoods = new ArrayList<>();

    @Transient
    public Long getTotalOrderPrice() {
        long total = 0;
        List<OrderFood> orderFoods = getOrderFoods();
        for (OrderFood food : orderFoods) {
            total += food.getTotalPrice();
        }
        return total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderFood> getOrderFoods() {
        return orderFoods;
    }

    public void setOrderFoods(List<OrderFood> orderFoods) {
        this.orderFoods = orderFoods;
    }

    @Transient
    public int getNumberOfProducts() {
        return this.orderFoods.size();
    }
}
