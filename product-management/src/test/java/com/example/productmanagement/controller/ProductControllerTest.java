package com.example.productmanagement.controller;

import com.example.productmanagement.dto.ProductDTO;
import com.example.productmanagement.entity.Image;
import com.example.productmanagement.service.ImageService;
import com.example.productmanagement.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc(addFilters = false)   // Désactive la sécurité Spring Boot 3
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private ImageService imageService;

    @Test
    void testGetAllProducts() throws Exception {
        when(productService.findAllProduct()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk());

        verify(productService, times(1)).findAllProduct();
    }

    @Test
    void testDeleteProduct() throws Exception {
        doNothing().when(productService).delete(1L);

        mockMvc.perform(delete("/api/v1/products/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testSearchByCategory() throws Exception {
        when(productService.searchByCategory(5L)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/products/prodscat/5"))
                .andExpect(status().isOk());
    }

    @Test
    void testSearchByName() throws Exception {
        when(productService.searchByName("lap")).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/products/prodsByName/lap"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateProduct() throws Exception {
        ProductDTO dto = ProductDTO.builder().productId(1L).productName("Phone").build();

        when(productService.create(any())).thenReturn(dto);

        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"productName\":\"Phone\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(1L));
    }

    @Test
    void testUploadImageFS() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "image", "test.png", "image/png", "abc".getBytes()
        );

        Image image = Image.builder().imageId(1L).name("test.png").build();

        when(imageService.uplaodImageProd(any(), eq(10L))).thenReturn(image);

        mockMvc.perform(multipart("/api/v1/products/image/uploadFS/10")
                        .file(file))
                .andExpect(status().isOk());
    }
}
