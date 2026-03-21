package com.example.productmanagement.mapper;

import com.example.productmanagement.dto.CategoryDTO;
import com.example.productmanagement.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toCategory(CategoryDTO request) {
        return Category.builder()
                .categoryId(request.getCategoryId())
                .categoryName(request.getCategoryName())
                .categoryDescription(request.getCategoryDescription())
                .build();
    }

    public CategoryDTO toResponse(Category category) {
        return CategoryDTO.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .categoryDescription(category.getCategoryDescription())
                .build();
    }
}