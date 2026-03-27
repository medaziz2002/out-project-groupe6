package com.example.productmanagement.service;

import com.example.productmanagement.dto.CategoryDTO;
import com.example.productmanagement.entity.Category;
import com.example.productmanagement.mapper.CategoryMapper;
import com.example.productmanagement.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository repo;

    @Mock
    private CategoryMapper mapper;

    @InjectMocks
    private CategoryService service;

    public CategoryServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCategoryById() {
        Category category = Category.builder()
                .categoryId(1L)
                .categoryName("Electronics")
                .build();

        CategoryDTO dto = CategoryDTO.builder()
                .categoryId(1L)
                .categoryName("Electronics")
                .build();

        when(repo.findById(1L)).thenReturn(Optional.of(category));
        when(mapper.toResponse(category)).thenReturn(dto);

        CategoryDTO result = service.getCategoryById(1L);

        assertEquals(dto.getCategoryId(), result.getCategoryId());
    }
}
