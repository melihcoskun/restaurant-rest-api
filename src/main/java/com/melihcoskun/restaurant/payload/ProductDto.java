package com.melihcoskun.restaurant.payload;

import lombok.Data;

@Data
public class ProductDto {

    private long id;


    private String name;
    private String description;
    private double price;
}
