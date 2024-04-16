package com.melihcoskun.restaurant.service;

import com.melihcoskun.restaurant.payload.RestaurantDto;

import java.util.List;

public interface RestaurantService {

    RestaurantDto createRestaurant(RestaurantDto restaurantDto);

    List<RestaurantDto> getAllRestaurants();

    RestaurantDto getRestaurantById(long id);

    RestaurantDto updateRestaurant(RestaurantDto restaurantDto,long id);

    void deletePRestaurantById(long id);

}
