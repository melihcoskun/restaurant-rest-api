package com.melihcoskun.restaurant.service.impl;

import com.melihcoskun.restaurant.entity.Category;
import com.melihcoskun.restaurant.entity.Product;
import com.melihcoskun.restaurant.entity.Restaurant;
import com.melihcoskun.restaurant.exception.ResourceNotFoundException;
import com.melihcoskun.restaurant.exception.RestaurantAPIException;
import com.melihcoskun.restaurant.payload.ProductDto;
import com.melihcoskun.restaurant.repository.CategoryRepository;
import com.melihcoskun.restaurant.repository.ProductRepository;
import com.melihcoskun.restaurant.repository.RestaurantRepository;
import com.melihcoskun.restaurant.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {



    private ProductRepository productRepository;
    private RestaurantRepository restaurantRepository;
    private CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository
            , RestaurantRepository restaurantRepository
    , CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.restaurantRepository = restaurantRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public ProductDto createProduct(long restaurantId, long categoryId, ProductDto productDto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new ResourceNotFoundException("Restaurant","id",restaurantId));

        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category", "id", categoryId));

        if(!category.getRestaurant().getId().equals(restaurant.getId())) {
            throw new RestaurantAPIException(HttpStatus.BAD_REQUEST,"Category does not belong to restaurant");
        }

        Product product = mapToEntity(productDto);
        product.setCategory(category);

        Product newProduct = productRepository.save(product);

        return mapToDto(newProduct);

    }
    /*
        @Override
        public ProductDto createProduct(long restaurantId, ProductDto productDto) {

            Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new ResourceNotFoundException("Restaurant","id",restaurantId));
            Product product = mapToEntity(productDto);

            product.setRestaurant(restaurant);

            Product newProduct = productRepository.save(product);

            return mapToDto(newProduct);
        }

        @Override
        public List<ProductDto> getProductsByRestaurantId(long restaurantId) {

            Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new ResourceNotFoundException("Restaurant","id",restaurantId));
            List<Product> products = productRepository.findByRestaurantId(restaurantId);

            return products.stream().map(this::mapToDto).collect(Collectors.toList());
        }

        @Override
        public ProductDto getProductById(long restaurantId, long productId) {
            Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new ResourceNotFoundException("Restaurant","id",restaurantId));

            Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product","id",productId));

            if(!product.getRestaurant().getId().equals(restaurant.getId())) {

                throw new RestaurantAPIException(HttpStatus.BAD_REQUEST,"Product does not belong to restaurant");
            }

            return mapToDto(product);

        }

        @Override
        public ProductDto updateProduct(long restaurantId, long productId, ProductDto productDto) {

            Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new ResourceNotFoundException("Restaurant","id",restaurantId));

            Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product","id",productId));

            if(!product.getRestaurant().getId().equals(restaurant.getId())) {

                throw new RestaurantAPIException(HttpStatus.BAD_REQUEST,"Product does not belong to restaurant");
            }

            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setName(productDto.getName());

            Product updatedProduct = productRepository.save(product);

            return mapToDto(updatedProduct);
        }

        @Override
        public void deleteProduct(long restaurantId, long productId) {

            Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new ResourceNotFoundException("Restaurant","id",restaurantId));

            Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product","id",productId));

            if(!product.getRestaurant().getId().equals(restaurant.getId())) {

                throw new RestaurantAPIException(HttpStatus.BAD_REQUEST,"Product does not belong to restaurant");
            }

            productRepository.delete(product);
        }
    */
    private Product mapToEntity(ProductDto productDto) {

        Product product = new Product();
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        product.setId(productDto.getId());

        return product;
    }

    private ProductDto mapToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setName(product.getName());
        productDto.setId(product.getId());
        return productDto;
    }
}
