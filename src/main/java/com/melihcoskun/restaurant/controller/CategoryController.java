package com.melihcoskun.restaurant.controller;


import com.melihcoskun.restaurant.entity.Category;
import com.melihcoskun.restaurant.payload.CategoryDto;
import com.melihcoskun.restaurant.service.CategoryService;
import com.melihcoskun.restaurant.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class CategoryController {


    private CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;

    }

    @PostMapping("/{restaurantId}/category")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto,
                                                      @PathVariable Long restaurantId){

        return ResponseEntity.ok(categoryService.createCategory(categoryDto,restaurantId));
    }

    @GetMapping("/{restaurantId}/category")
    public ResponseEntity<List<CategoryDto>> getCategoriesByRestaurantId(@PathVariable Long restaurantId){

        return ResponseEntity.ok(categoryService.getCategoriesByRestaurantId(restaurantId));
    }
}
