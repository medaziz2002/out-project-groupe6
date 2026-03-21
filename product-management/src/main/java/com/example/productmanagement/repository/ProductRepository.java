package com.example.productmanagement.repository;

import com.example.productmanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    
    List<Product> findByProductNameContainingIgnoreCase(String name);
List<Product> findByCategoryCategoryId(Long categoryId);


}