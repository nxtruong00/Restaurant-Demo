package com.truongnx.restaurantmanager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "foods")
public class Food {

    @Id
    private String id;

    @NotNull(message="Food name is required")
    @Column(name = "food_name")
    private String name;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "food_description", length = 500)
    private String description;

    @Column(name = "food_price")
    private long price;

}
