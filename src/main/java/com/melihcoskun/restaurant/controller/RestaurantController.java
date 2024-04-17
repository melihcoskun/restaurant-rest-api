package com.melihcoskun.restaurant.controller;


import com.melihcoskun.restaurant.payload.RestaurantDto;
import com.melihcoskun.restaurant.payload.RestaurantResponse;
import com.melihcoskun.restaurant.service.RestaurantService;
import com.melihcoskun.restaurant.utils.AppConstants;
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
    public ResponseEntity<RestaurantResponse> getAllRestaurants(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy" , defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){

        return ResponseEntity.ok(restaurantService.getAllRestaurants(pageNo, pageSize,sortBy, sortDir));
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
