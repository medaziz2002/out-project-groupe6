package com.example.productmanagement.service;

import com.example.productmanagement.dto.CategoryDTO;
import com.example.productmanagement.mapper.CategoryMapper;
import com.example.productmanagement.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toResponse)
                .collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    public CategoryDTO createCategory(CategoryDTO request) {
        return categoryMapper.toResponse(
                categoryRepository.save(categoryMapper.toCategory(request))
        );
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO request) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        request.setCategoryId(id);
        return categoryMapper.toResponse(
                categoryRepository.save(categoryMapper.toCategory(request))
        );
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}