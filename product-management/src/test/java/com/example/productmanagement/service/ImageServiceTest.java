package com.example.productmanagement.service;

import com.example.productmanagement.entity.Image;
import com.example.productmanagement.entity.Product;
import com.example.productmanagement.repository.ImageRepository;
import com.example.productmanagement.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ImageServiceTest {

    @Mock
    private ImageRepository imageRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ImageService imageService;

    public ImageServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetImageDetails() throws IOException {
        Image image = Image.builder()
                .imageId(1L)
                .name("test.png")
                .type("image/png")
                .image(new byte[]{1, 2, 3})
                .build();

        when(imageRepository.findById(1L)).thenReturn(Optional.of(image));

        Image result = imageService.getImageDetails(1L);

        assertEquals(image.getImageId(), result.getImageId());
        assertEquals(image.getName(), result.getName());
        assertEquals(image.getType(), result.getType());
    }

    @Test
    void testGetImage() throws IOException {
        Image image = Image.builder()
                .imageId(1L)
                .name("test.png")
                .type("image/png")
                .image(new byte[]{1, 2, 3})
                .build();

        when(imageRepository.findById(1L)).thenReturn(Optional.of(image));

        ResponseEntity<byte[]> response = imageService.getImage(1L);

        assertEquals(MediaType.valueOf("image/png"), response.getHeaders().getContentType());
        assertArrayEquals(image.getImage(), response.getBody());
    }

    @Test
    void testDeleteImage() {
        doNothing().when(imageRepository).deleteById(1L);

        imageService.deleteImage(1L);

        verify(imageRepository, times(1)).deleteById(1L);
    }
}
