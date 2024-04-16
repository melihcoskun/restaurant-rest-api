package com.melihcoskun.restaurant.repository;

import com.melihcoskun.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {


}
