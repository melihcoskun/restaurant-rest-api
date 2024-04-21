package com.melihcoskun.restaurant.repository;

import com.melihcoskun.restaurant.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query("SELECT c FROM Category c " +
            "JOIN c.restaurants r " +
            "WHERE r.id= :id")
    List<Category> findCategoriesByRestaurantId(long id);
}
