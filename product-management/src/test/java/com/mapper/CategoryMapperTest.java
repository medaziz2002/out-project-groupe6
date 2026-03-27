package com.example.productmanagement.mapper;

import com.example.productmanagement.dto.CategoryDTO;
import com.example.productmanagement.entity.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    private final CategoryMapper mapper = new CategoryMapper();

    @Test
    void testToCategory() {
        CategoryDTO dto = CategoryDTO.builder()
                .categoryId(1L)
                .categoryName("Electronics")
                .categoryDescription("Devices")
                .build();

        Category category = mapper.toCategory(dto);

        assertEquals(dto.getCategoryId(), category.getCategoryId());
        assertEquals(dto.getCategoryName(), category.getCategoryName());
        assertEquals(dto.getCategoryDescription(), category.getCategoryDescription());
    }

    @Test
    void testToResponse() {
        Category category = Category.builder()
                .categoryId(2L)
                .categoryName("Books")
                .categoryDescription("Reading")
                .build();

        CategoryDTO dto = mapper.toResponse(category);

        assertEquals(category.getCategoryId(), dto.getCategoryId());
        assertEquals(category.getCategoryName(), dto.getCategoryName());
        assertEquals(category.getCategoryDescription(), dto.getCategoryDescription());
    }
}
