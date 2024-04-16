package com.melihcoskun.restaurant.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "restaurants" , uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
public class Restaurant {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double rating;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;
}
