package com.example.productmanagement.service;

import com.example.productmanagement.dto.CategoryDTO;
import com.example.productmanagement.entity.Category;
import com.example.productmanagement.mapper.CategoryMapper;
import com.example.productmanagement.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryMapper categoryMapper;
    @InjectMocks
    private CategoryService categoryService;
    private Category category;
    private CategoryDTO categoryDTO;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setCategoryId(1L);
        category.setCategoryName("Categorie-1");
        category.setCategoryDescription("Description-1");
        category.setProducts(List.of());
        categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(1L);
        categoryDTO.setCategoryName("Categorie-1");
        categoryDTO.setCategoryDescription("Description-1");
    }

    @Test
    void createCategory_shouldReturnCategory() {
        when(categoryMapper.toCategory(categoryDTO)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryMapper.toResponse(category)).thenReturn(categoryDTO);
        CategoryDTO result = categoryService.createCategory(categoryDTO);

        assertNotNull(result);
        assertEquals("Categorie-1", result.getCategoryName());
        verify(categoryMapper).toCategory(categoryDTO);
        verify(categoryRepository).save(category);
        verify(categoryMapper).toResponse(category);
    }

    @Test
    void getCategoryById_shouldReturnCategory() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryMapper.toResponse(category)).thenReturn(categoryDTO);
        CategoryDTO result = categoryService.getCategoryById(1L);

        assertNotNull(result);
        assertEquals("Categorie-1", result.getCategoryName());
        verify(categoryRepository).findById(1L);
        verify(categoryMapper).toResponse(category);
    }

    @Test
    void getAllCategories_shouldReturnList() {
        when(categoryRepository.findAll()).thenReturn(List.of(category));
        when(categoryMapper.toResponse(category)).thenReturn(categoryDTO);
        List<CategoryDTO> result = categoryService.getAllCategories();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Categorie-1", result.get(0).getCategoryName());
        verify(categoryRepository).findAll();
        verify(categoryMapper).toResponse(category);
    }

    @Test
    void updateCategory_shouldUpdateCategory() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryMapper.toCategory(categoryDTO)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryMapper.toResponse(category)).thenReturn(categoryDTO);

        CategoryDTO result = categoryService.updateCategory(1L, categoryDTO);
        assertNotNull(result);
        assertEquals("Categorie-1", result.getCategoryName());
        verify(categoryRepository).findById(1L);
        verify(categoryMapper).toCategory(categoryDTO);
        verify(categoryRepository).save(category);
        verify(categoryMapper).toResponse(category);
    }

    @Test
    void deleteCategory_shouldCallRepositoryDelete() {
        doNothing().when(categoryRepository).deleteById(1L);
        categoryService.deleteCategory(1L);
        verify(categoryRepository).deleteById(1L);
    }
}