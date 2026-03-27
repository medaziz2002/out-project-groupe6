package com.example.productmanagement.mapper;

import com.example.productmanagement.dto.ProductDTO;
import com.example.productmanagement.entity.Product;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    private final ImageMapper imageMapper = new ImageMapper();
    private final ProductMapper mapper = new ProductMapper(imageMapper);

    @Test
    void testToProduct() {
        ProductDTO dto = ProductDTO.builder()
                .productId(1L)
                .productName("Laptop")
                .productPrice(999.99)
                .creationDate(new Date())
                .build();

        Product product = mapper.toProduct(dto);

        assertEquals(dto.getProductId(), product.getProductId());
        assertEquals(dto.getProductName(), product.getProductName());
        assertEquals(dto.getProductPrice(), product.getProductPrice());
    }

    @Test
    void testToResponse() {
        Product product = Product.builder()
                .productId(2L)
                .productName("Phone")
                .productPrice(499.99)
                .creationDate(new Date())
                .build();

        ProductDTO dto = mapper.toResponse(product);

        assertEquals(product.getProductId(), dto.getProductId());
        assertEquals(product.getProductName(), dto.getProductName());
        assertEquals(product.getProductPrice(), dto.getProductPrice());
    }
}
