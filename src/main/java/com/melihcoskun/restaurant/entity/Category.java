package com.melihcoskun.restaurant.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "categories"
)
public class Category {


    @Id
    @GeneratedValue
    private Long id;
    private String name;



    @ManyToMany(
            fetch = FetchType.LAZY, cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "category_restaurant",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id")

    )
    private List<Restaurant> restaurants;

    public void addRestaurant(Restaurant restaurant) {
        if(restaurants == null) {
            restaurants = new ArrayList<>();
        }
        restaurants.add(restaurant);

    }



}
