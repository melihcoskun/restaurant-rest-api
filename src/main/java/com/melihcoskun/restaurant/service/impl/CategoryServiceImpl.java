package com.melihcoskun.restaurant.service.impl;

import com.melihcoskun.restaurant.entity.Category;
import com.melihcoskun.restaurant.entity.Restaurant;
import com.melihcoskun.restaurant.exception.ResourceNotFoundException;
import com.melihcoskun.restaurant.payload.CategoryDto;
import com.melihcoskun.restaurant.repository.CategoryRepository;
import com.melihcoskun.restaurant.repository.RestaurantRepository;
import com.melihcoskun.restaurant.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {


    private RestaurantRepository restaurantRepository;
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(RestaurantRepository restaurantRepository, CategoryRepository categoryRepository) {
        this.restaurantRepository = restaurantRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto, long restaurantId) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new ResourceNotFoundException("Restaurant","id",restaurantId));

        Category category = mapToEntity(categoryDto);

        category.addRestaurant(restaurant);
        Category savedCategory = categoryRepository.save(category);



        return mapToDto(savedCategory);
    }

    @Override
    public List<CategoryDto> getCategoriesByRestaurantId(Long restaurantId) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new ResourceNotFoundException("Restaurant","id",restaurantId));
        List<Category> categories = categoryRepository.findCategoriesByRestaurantId(restaurantId);

        return categories.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private CategoryDto mapToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
    categoryDto.setId(category.getId());
        return categoryDto;
    }
    private Category mapToEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());

        return category;
    }
}
