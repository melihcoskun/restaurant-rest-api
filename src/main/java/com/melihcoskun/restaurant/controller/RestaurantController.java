package com.melihcoskun.restaurant.controller;


import com.melihcoskun.restaurant.payload.RestaurantDto;
import com.melihcoskun.restaurant.service.RestaurantService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {


    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody RestaurantDto restaurantDto ){

        return new ResponseEntity<>(restaurantService.createRestaurant(restaurantDto), HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants(){

        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable long id){

        return ResponseEntity.ok(restaurantService.getRestaurantById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDto> updateRestaurant(@RequestBody RestaurantDto restaurantDto,
                                                          @PathVariable long id){

        return ResponseEntity.ok(restaurantService.updateRestaurant(restaurantDto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable long id) {

        restaurantService.deletePRestaurantById(id);
        return ResponseEntity.ok("Restaurant entity deleted succesfully");

    }
}
