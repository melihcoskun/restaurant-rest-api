package com.melihcoskun.restaurant.repository;

import com.melihcoskun.restaurant.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByCategoryId(long categoryId);

    @Query("SELECT p FROM Product p " +
            "INNER JOIN p.category c " +
            "WHERE c.name= :categoryName")
    List<Product> findByCategoryName(String categoryName);
}
