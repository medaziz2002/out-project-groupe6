package com.example.productmanagement.repository;

import com.example.productmanagement.entity.Category;
import com.example.productmanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
