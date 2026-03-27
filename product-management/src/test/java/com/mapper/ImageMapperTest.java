package com.example.productmanagement.mapper;

import com.example.productmanagement.dto.ImageDTO;
import com.example.productmanagement.entity.Image;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageMapperTest {

    private final ImageMapper mapper = new ImageMapper();

    @Test
    void testToImage() {
        ImageDTO dto = ImageDTO.builder()
                .imageId(1L)
                .name("photo.jpg")
                .type("image/jpeg")
                .image(new byte[]{1, 2, 3})
                .productId(10L)
                .build();

        Image image = mapper.toImage(dto);

        assertEquals(dto.getImageId(), image.getImageId());
        assertEquals(dto.getName(), image.getName());
        assertEquals(dto.getType(), image.getType());
        assertArrayEquals(dto.getImage(), image.getImage());
        assertEquals(dto.getProductId(), image.getProduct().getProductId());
    }

    @Test
    void testToResponse() {
        Image image = Image.builder()
                .imageId(2L)
                .name("test.png")
                .type("image/png")
                .image(new byte[]{4, 5, 6})
                .build();

        ImageDTO dto = mapper.toResponse(image);

        assertEquals(image.getImageId(), dto.getImageId());
        assertEquals(image.getName(), dto.getName());
        assertEquals(image.getType(), dto.getType());
        assertArrayEquals(image.getImage(), dto.getImage());
    }
}
