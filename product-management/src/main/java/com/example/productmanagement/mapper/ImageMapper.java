package com.example.productmanagement.mapper;

import com.example.productmanagement.dto.ImageDTO;
import com.example.productmanagement.entity.Image;
import com.example.productmanagement.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {

    public Image toImage(ImageDTO request) {
        return Image.builder()
                .imageId(request.getImageId())
                .name(request.getName())
                .type(request.getType())
                .image(request.getImage())
                .product(
                        Product.builder()
                                .productId(request.getProductId())
                                .build()
                )
                .build();
    }

    public ImageDTO toResponse(Image image) {
        return ImageDTO.builder()
                .imageId(image.getImageId())
                .name(image.getName())
                .type(image.getType())
                .image(image.getImage())
                .productId(
                        image.getProduct() != null ? image.getProduct().getProductId() : null
                )
                .build();
    }
}