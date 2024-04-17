package com.melihcoskun.restaurant.service;

import com.melihcoskun.restaurant.payload.RestaurantDto;
import com.melihcoskun.restaurant.payload.RestaurantResponse;

import java.util.List;

public interface RestaurantService {

    RestaurantDto createRestaurant(RestaurantDto restaurantDto);

    RestaurantResponse getAllRestaurants(int pageNo, int pageSize, String sortBy, String sortDir);

    RestaurantDto getRestaurantById(long id);

    RestaurantDto updateRestaurant(RestaurantDto restaurantDto,long id);

    void deletePRestaurantById(long id);

}
