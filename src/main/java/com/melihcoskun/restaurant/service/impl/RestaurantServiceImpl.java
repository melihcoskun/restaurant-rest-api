package com.melihcoskun.restaurant.service.impl;

import com.melihcoskun.restaurant.entity.Restaurant;
import com.melihcoskun.restaurant.exception.ResourceNotFoundException;
import com.melihcoskun.restaurant.payload.RestaurantDto;
import com.melihcoskun.restaurant.payload.RestaurantResponse;
import com.melihcoskun.restaurant.repository.RestaurantRepository;
import com.melihcoskun.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public RestaurantResponse getAllRestaurants(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);


        Page<Restaurant> restaurants = restaurantRepository.findAll(pageable);
        List<Restaurant> listOfRestaurants = restaurants.getContent();
        List<RestaurantDto> content  = listOfRestaurants.stream().map(this::mapToDto).collect(Collectors.toList());
        RestaurantResponse restaurantResponse = new RestaurantResponse();
        restaurantResponse.setContent(content);
        restaurantResponse.setPageNo(restaurants.getNumber());
        restaurantResponse.setPageSize(restaurants.getSize());
        restaurantResponse.setTotalElements(restaurants.getTotalElements());
        restaurantResponse.setTotalPages(restaurants.getTotalPages());
        restaurantResponse.setLast(restaurants.isLast());

        return restaurantResponse;

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
