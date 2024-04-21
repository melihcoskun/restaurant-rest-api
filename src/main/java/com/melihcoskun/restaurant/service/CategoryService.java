package com.melihcoskun.restaurant.service;

import com.melihcoskun.restaurant.entity.Category;
import com.melihcoskun.restaurant.payload.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto, long restaurantId);

    List<CategoryDto> getCategoriesByRestaurantId(Long restaurantId);
}
