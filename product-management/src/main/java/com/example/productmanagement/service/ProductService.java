package com.example.productmanagement.service;


import com.example.productmanagement.dto.ProductDTO;
import com.example.productmanagement.entity.Image;
import com.example.productmanagement.entity.Product;
import com.example.productmanagement.mapper.ProductMapper;
import com.example.productmanagement.repository.CategoryRepository;
import com.example.productmanagement.repository.ImageRepository;
import com.example.productmanagement.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {



    private final  ProductRepository repository;
    private final ImageRepository imageRepository;

    private final ProductMapper mapper;
    private final CategoryRepository categoryRepository;
    public List<ProductDTO> findAllProduct() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    


    public ProductDTO create(ProductDTO productDTO) {
        var product = mapper.toProduct(productDTO);


        if (productDTO.getCategoryId() != null) {
            var category = categoryRepository.findById(productDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found: " + productDTO.getCategoryId()));
            product.setCategory(category);
        }

        var savedProduct = repository.save(product);
        return mapper.toResponse(savedProduct);
    }


    public Product getProduct(Long id) {
        return  repository.findById(id).get();

    }

    public void delete(Long id) {
        Product p = getProduct(id);

        for (Image image : p.getImages()) {
            try {
                Path imagePath = Paths.get(System.getProperty("user.home") + "/images/" + image.getName());
                Files.deleteIfExists(imagePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        imageRepository.deleteAll(p.getImages());
        repository.deleteById(id);
    }

public List<ProductDTO> searchByName(String name) {
    return repository.findByProductNameContainingIgnoreCase(name)
            .stream()
            .map(mapper::toResponse)
            .collect(Collectors.toList());
}
public List<ProductDTO> searchByCategory(Long categoryId) {
    return repository.findByCategoryCategoryId(categoryId)
            .stream()
            .map(mapper::toResponse)
            .collect(Collectors.toList());
}

    public ProductDTO updateProduct(Long id, ProductDTO request) {
        Product exist=repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        exist.setProductName(request.getProductName());
        exist.setProductPrice(request.getProductPrice());
        exist.setCreationDate(request.getCreationDate());

        if(request.getCategoryId() != null){
            var category=categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("product not found with id: " + id));
            exist.setCategory(category);
        }

        return mapper.toResponse(repository.save(exist));
    }


}
