package com.example.productmanagement.mapper;

import com.example.productmanagement.dto.ProductDTO;
import com.example.productmanagement.entity.Category;

import com.example.productmanagement.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component

public class ProductMapper {


    @Autowired
    private  ImageMapper imageMapper;


    public Product toProduct(ProductDTO request) {
        return Product.builder()
                .productId(request.getProductId())
                .productName(request.getProductName())
                .productPrice(request.getProductPrice())
                .creationDate(request.getCreationDate())
                .category(
                        Category.builder()
                                .categoryId(request.getCategoryId())
                                .build()
                )
                .build();
    }

    public ProductDTO toResponse(Product product) {

        return ProductDTO.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .creationDate(product.getCreationDate())
                .categoryId(
                        product.getCategory() != null ? product.getCategory().getCategoryId() : null
                )
                .categoryName(
                        product.getCategory() != null ? product.getCategory().getCategoryName() : null
                )
                .images(
                        product.getImages()
                                .stream()
                                .map(imageMapper::toResponse)
                                .toList()
                )
                .build();
    }
}
