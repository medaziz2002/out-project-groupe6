package com.example.productmanagement.controller;

import com.example.productmanagement.dto.ProductDTO;
import com.example.productmanagement.entity.Image;
import com.example.productmanagement.service.ImageService;
import com.example.productmanagement.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ImageService imageService;

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.findAllProduct();
    }
    @DeleteMapping("/{id}")
public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    productService.delete(id);
    return ResponseEntity.noContent().build();
}

@GetMapping("/prodscat/{categoryId}")
public List<ProductDTO> searchByCategory(@PathVariable Long categoryId) {
    return productService.searchByCategory(categoryId);
}




    @GetMapping("/prodsByName/{name}")
public List<ProductDTO> searchByName(@PathVariable String name) {
    return productService.searchByName(name);
}

    @PostMapping
    public ProductDTO save(@RequestBody ProductDTO productDTO) {
        return productService.create(productDTO);
    }

    @PostMapping("/image/uploadFS/{productId}")
    public ResponseEntity<?> uploadImageFS(
            @RequestParam("image") MultipartFile file,
            @PathVariable("productId") Long productId) throws IOException {
        Image image = imageService.uplaodImageProd(file, productId);
        return ResponseEntity.ok(image);
    }

    @GetMapping("/image/get/info/{id}")
    public ResponseEntity<Image> getImageDetails(
            @PathVariable("id") Long id) throws IOException {
        return ResponseEntity.ok(imageService.getImageDetails(id));
    }

    @DeleteMapping("/image/delete/{id}")
    public ResponseEntity<?> deleteImage(
            @PathVariable("id") Long id) {
        imageService.deleteImage(id);
        return ResponseEntity.ok("Image deleted");
    }

    @GetMapping("/image/loadfromFS/{productId}")
    public ResponseEntity<byte[]> loadImage(
            @PathVariable("productId") Long productId) throws IOException {
        var images = imageService.getImagesParProd(productId);
        if (images == null || images.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return imageService.getImage(images.get(0).getImageId());
    }
}