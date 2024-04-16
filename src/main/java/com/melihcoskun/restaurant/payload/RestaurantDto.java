package com.melihcoskun.restaurant.payload;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RestaurantDto {
    private Long id;
    private String name;
    private double rating;
    private String address;
    private String phone;

}
