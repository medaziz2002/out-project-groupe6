package com.example.productmanagement.controller;

import com.example.productmanagement.dto.CategoryDTO;
import com.example.productmanagement.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CategoryController.class)
@AutoConfigureMockMvc(addFilters = false)   // Désactive la sécurité Spring Boot 3
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    void testGetAllCategories() throws Exception {
        when(categoryService.getAllCategories()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/categories"))
                .andExpect(status().isOk());

        verify(categoryService, times(1)).getAllCategories();
    }

    @Test
    void testGetCategoryById() throws Exception {
        CategoryDTO dto = CategoryDTO.builder()
                .categoryId(1L)
                .categoryName("Electronics")
                .build();

        when(categoryService.getCategoryById(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/categories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId").value(1L));
    }

    @Test
    void testCreateCategory() throws Exception {
        CategoryDTO dto = CategoryDTO.builder()
                .categoryId(1L)
                .categoryName("Books")
                .build();

        when(categoryService.createCategory(any())).thenReturn(dto);

        mockMvc.perform(post("/api/v1/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"categoryName\":\"Books\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.categoryName").value("Books"));
    }

    @Test
    void testUpdateCategory() throws Exception {
        CategoryDTO dto = CategoryDTO.builder()
                .categoryId(1L)
                .categoryName("Updated")
                .build();

        when(categoryService.updateCategory(eq(1L), any())).thenReturn(dto);

        mockMvc.perform(put("/api/v1/categories/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"categoryName\":\"Updated\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryName").value("Updated"));
    }

    @Test
    void testDeleteCategory() throws Exception {
        doNothing().when(categoryService).deleteCategory(1L);

        mockMvc.perform(delete("/api/v1/categories/1"))
                .andExpect(status().isNoContent());

        verify(categoryService, times(1)).deleteCategory(1L);
    }
}
