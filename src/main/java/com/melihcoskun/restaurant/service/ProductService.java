package com.melihcoskun.restaurant.service;

import com.melihcoskun.restaurant.entity.Product;
import com.melihcoskun.restaurant.payload.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(long restaurantId,long categoryId, ProductDto productDto);

   /* List<ProductDto> getProductsByRestaurantId(long restaurantId);

    ProductDto getProductById(long restaurantId, long productId);

    ProductDto updateProduct(long restaurantId, long productId, ProductDto productDto);

    void deleteProduct(long restaurantId, long productId);

 */


}
