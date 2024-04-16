package com.melihcoskun.restaurant.service.impl;

import com.melihcoskun.restaurant.entity.Restaurant;
import com.melihcoskun.restaurant.exception.ResourceNotFoundException;
import com.melihcoskun.restaurant.payload.RestaurantDto;
import com.melihcoskun.restaurant.repository.RestaurantRepository;
import com.melihcoskun.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RestaurantServiceImpl implements RestaurantService {


    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public RestaurantDto createRestaurant(RestaurantDto restaurantDto) {

        Restaurant restaurant = mapToEntity(restaurantDto);
        Restaurant newRestaurant = restaurantRepository.save(restaurant);

        return mapToDto(newRestaurant);
    }

    @Override
    public List<RestaurantDto> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public RestaurantDto getRestaurantById(long id) {

        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Restaurant","id",id));
        return mapToDto(restaurant);
    }

    @Override
    public RestaurantDto updateRestaurant(RestaurantDto restaurantDto, long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Restaurant","id",id));
        restaurant.setPhone(restaurantDto.getPhone());
        restaurant.setName(restaurantDto.getName());
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setRating(restaurantDto.getRating());

        Restaurant updatedRestaurant = restaurantRepository.save(restaurant);
        return mapToDto(updatedRestaurant);
    }

    @Override
    public void deletePRestaurantById(long id) {

        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Restaurant","id",id));
        restaurantRepository.delete(restaurant);
    }

    private Restaurant mapToEntity(RestaurantDto restaurantDto) {

        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setName(restaurantDto.getName());
        restaurant.setRating(restaurantDto.getRating());
        restaurant.setPhone(restaurantDto.getPhone());

        return restaurant;
    }

    private RestaurantDto mapToDto(Restaurant restaurant) {
        RestaurantDto restaurantDto = new RestaurantDto();

        restaurantDto.setAddress(restaurant.getAddress());
        restaurantDto.setPhone(restaurant.getPhone());
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setName(restaurant.getName());
        restaurantDto.setRating(restaurant.getRating());

        return restaurantDto;
    }
}
