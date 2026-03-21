package com.example.productmanagement.mapper;

import com.example.productmanagement.dto.CategoryDTO;
import com.example.productmanagement.dto.ProductDTO;
import com.example.productmanagement.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final ImageMapper imageMapper;

    public Product toProduct(ProductDTO request) {
        return Product.builder()
                .productId(request.getProductId())
                .productName(request.getProductName())
                .productPrice(request.getProductPrice())
                .creationDate(request.getCreationDate())
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
                .categoryDTO(
                        product.getCategory() != null ?
                                CategoryDTO.builder()
                                        .categoryId(product.getCategory().getCategoryId())
                                        .categoryName(product.getCategory().getCategoryName())
                                        .categoryDescription(product.getCategory().getCategoryDescription())
                                        .build()
                                : null
                )
                .images(
                        product.getImages() != null ?
                                product.getImages()
                                        .stream()
                                        .map(imageMapper::toResponse)
                                        .toList()
                                : List.of()
                )
                .build();
    }
}