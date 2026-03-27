package com.example.productmanagement.entity;

import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testProductSettersAndGetters() {
        Product product = new Product();

        Date now = new Date();
        Category category = new Category();

        product.setProductId(1L);
        product.setProductName("Laptop");
        product.setProductPrice(999.99);
        product.setCreationDate(now);
        product.setCategory(category);

        assertEquals(1L, product.getProductId());
        assertEquals("Laptop", product.getProductName());
        assertEquals(999.99, product.getProductPrice());
        assertEquals(now, product.getCreationDate());
        assertEquals(category, product.getCategory());
    }

    @Test
    void testProductBuilder() {
        Date now = new Date();
        Category category = new Category();

        Product product = Product.builder()
                .productId(2L)
                .productName("Phone")
                .productPrice(499.99)
                .creationDate(now)
                .category(category)
                .build();

        assertEquals(2L, product.getProductId());
        assertEquals("Phone", product.getProductName());
        assertEquals(499.99, product.getProductPrice());
        assertEquals(now, product.getCreationDate());
        assertEquals(category, product.getCategory());
    }
}
