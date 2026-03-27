package com.example.productmanagement.service;

import com.example.productmanagement.dto.ProductDTO;
import com.example.productmanagement.entity.Category;
import com.example.productmanagement.entity.Product;
import com.example.productmanagement.mapper.ProductMapper;
import com.example.productmanagement.repository.CategoryRepository;
import com.example.productmanagement.repository.ImageRepository;
import com.example.productmanagement.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ImageRepository imageRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductMapper mapper;

    @InjectMocks
    private ProductService service;

    public ProductServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllProduct() {
        Product product = Product.builder()
                .productId(1L)
                .productName("Laptop")
                .build();

        ProductDTO dto = ProductDTO.builder()
                .productId(1L)
                .productName("Laptop")
                .build();

        when(productRepository.findAll()).thenReturn(List.of(product));
        when(mapper.toResponse(product)).thenReturn(dto);

        List<ProductDTO> result = service.findAllProduct();

        assertEquals(1, result.size());
        assertEquals("Laptop", result.get(0).getProductName());
    }

    @Test
    void testCreate() {
        ProductDTO dto = ProductDTO.builder()
                .productName("Phone")
                .categoryId(10L)
                .build();

        Product product = Product.builder().productName("Phone").build();
        Category category = Category.builder().categoryId(10L).build();
        Product saved = Product.builder().productId(1L).productName("Phone").category(category).build();
        ProductDTO response = ProductDTO.builder().productId(1L).productName("Phone").build();

        when(mapper.toProduct(dto)).thenReturn(product);
        when(categoryRepository.findById(10L)).thenReturn(Optional.of(category));
        when(productRepository.save(product)).thenReturn(saved);
        when(mapper.toResponse(saved)).thenReturn(response);

        ProductDTO result = service.create(dto);

        assertEquals(1L, result.getProductId());
    }

    @Test
    void testGetProduct() {
        Product product = Product.builder().productId(1L).build();
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product result = service.getProduct(1L);

        assertEquals(1L, result.getProductId());
    }

    @Test
    void testSearchByName() {
        Product product = Product.builder().productName("Laptop").build();
        ProductDTO dto = ProductDTO.builder().productName("Laptop").build();

        when(productRepository.findByProductNameContainingIgnoreCase("lap"))
                .thenReturn(List.of(product));
        when(mapper.toResponse(product)).thenReturn(dto);

        List<ProductDTO> result = service.searchByName("lap");

        assertEquals(1, result.size());
    }

    @Test
    void testSearchByCategory() {
        Product product = Product.builder().productId(1L).build();
        ProductDTO dto = ProductDTO.builder().productId(1L).build();

        when(productRepository.findByCategoryCategoryId(5L)).thenReturn(List.of(product));
        when(mapper.toResponse(product)).thenReturn(dto);

        List<ProductDTO> result = service.searchByCategory(5L);

        assertEquals(1, result.size());
    }
}
