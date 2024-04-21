package com.melihcoskun.restaurant.controller;


import com.melihcoskun.restaurant.entity.Product;
import com.melihcoskun.restaurant.payload.CategoryDto;
import com.melihcoskun.restaurant.payload.ProductDto;
import com.melihcoskun.restaurant.service.ProductService;
import com.melihcoskun.restaurant.service.RestaurantService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class ProductController {








    private ProductService productService;

    public ProductController(ProductService productService) {

        this.productService = productService;
    }

    @PostMapping("/{restaurantId}/categories/{categoryId}/products")
    public ResponseEntity<ProductDto> createProduct(@PathVariable Long restaurantId,
                                              @PathVariable Long categoryId,
                                              @RequestBody ProductDto productDto) {


        return new ResponseEntity<>(productService.createProduct(restaurantId,categoryId,productDto), HttpStatus.CREATED);
    }

    /*

    @PostMapping("/restaurants/{restaurantId}/products")
    public ResponseEntity<ProductDto> createProduct(@PathVariable long restaurantId,
                                                    @RequestBody ProductDto productDto) {

        return new ResponseEntity<>(productService.createProduct(restaurantId, productDto), HttpStatus.CREATED);

    }

    @GetMapping("/restaurants/{restaurantId}/products")
    public ResponseEntity<List<ProductDto>> getProductsByRestaurantId(@PathVariable long restaurantId){

        return ResponseEntity.ok(productService.getProductsByRestaurantId(restaurantId));
    }


    @GetMapping("/restaurants/{restaurantId}/products/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable long restaurantId,
                                                     @PathVariable long productId){

        return ResponseEntity.ok(productService.getProductById(restaurantId, productId));

    }

    @PutMapping("/restaurants/{restaurantId}/products/{productId}")

    public ResponseEntity<ProductDto> updateProduct(@PathVariable long restaurantId,
                                                    @PathVariable long productId,
                                                    @RequestBody ProductDto productDto){

        return ResponseEntity.ok(productService.updateProduct(restaurantId, productId, productDto));
    }

    @DeleteMapping("/restaurants/{restaurantId}/products/{productId}")

    public ResponseEntity<String> deleteProduct(@PathVariable long restaurantId,
                                                @PathVariable long productId) {

        productService.deleteProduct(restaurantId,productId);
        return ResponseEntity.ok("Deleted succesfully");
    }



*/


}
