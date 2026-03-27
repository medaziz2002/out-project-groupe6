package com.example.productmanagement.service;

import com.example.productmanagement.dto.CategoryDTO;
import com.example.productmanagement.entity.Category;
import com.example.productmanagement.mapper.CategoryMapper;
import com.example.productmanagement.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryService service;

    @Test
    void testGetAllCategories() {
        Category category = Category.builder()
                .categoryId(1L)
                .categoryName("Tech")
                .build();

        CategoryDTO dto = CategoryDTO.builder()
                .categoryId(1L)
                .categoryName("Tech")
                .build();

        when(categoryRepository.findAll()).thenReturn(List.of(category));
        when(categoryMapper.toResponse(category)).thenReturn(dto);

        List<CategoryDTO> result = service.getAllCategories();

        assertEquals(1, result.size());
        assertEquals("Tech", result.get(0).getCategoryName());
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

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryMapper.toResponse(category)).thenReturn(dto);

        CategoryDTO result = service.getCategoryById(1L);

        assertEquals("Electronics", result.getCategoryName());
    }

    @Test
    void testCreateCategory() {
        CategoryDTO request = CategoryDTO.builder()
                .categoryName("Books")
                .build();

        Category category = Category.builder()
                .categoryName("Books")
                .build();

        Category saved = Category.builder()
                .categoryId(1L)
                .categoryName("Books")
                .build();

        CategoryDTO response = CategoryDTO.builder()
                .categoryId(1L)
                .categoryName("Books")
                .build();

        when(categoryMapper.toCategory(request)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(saved);
        when(categoryMapper.toResponse(saved)).thenReturn(response);

        CategoryDTO result = service.createCategory(request);

        assertEquals(1L, result.getCategoryId());
    }

    @Test
    void testUpdateCategory() {
        CategoryDTO request = CategoryDTO.builder()
                .categoryName("Updated")
                .build();

        Category existing = Category.builder()
                .categoryId(1L)
                .categoryName("Old")
                .build();

        Category updated = Category.builder()
                .categoryId(1L)
                .categoryName("Updated")
                .build();

        CategoryDTO response = CategoryDTO.builder()
                .categoryId(1L)
                .categoryName("Updated")
                .build();

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(categoryMapper.toCategory(request)).thenReturn(updated);
        when(categoryRepository.save(updated)).thenReturn(updated);
        when(categoryMapper.toResponse(updated)).thenReturn(response);

        CategoryDTO result = service.updateCategory(1L, request);

        assertEquals("Updated", result.getCategoryName());
    }

    @Test
    void testUpdateCategoryNotFound() {
        CategoryDTO request = CategoryDTO.builder()
                .categoryName("X")
                .build();

        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.updateCategory(1L, request));
    }

    @Test
    void testDeleteCategory() {
        doNothing().when(categoryRepository).deleteById(1L);

        service.deleteCategory(1L);

        verify(categoryRepository, times(1)).deleteById(1L);
    }
}
