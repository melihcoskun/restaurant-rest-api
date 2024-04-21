package com.melihcoskun.restaurant.repository;

import com.melihcoskun.restaurant.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    //List<Product> findByRestaurantId(long id);
}
