package com.truongnx.restaurantmanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodForm {
    private String id;
    private String name;
    private MultipartFile image;
    private String description;
    private long price;
}
