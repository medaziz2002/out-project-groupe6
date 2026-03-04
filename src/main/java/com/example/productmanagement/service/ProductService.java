package com.example.productmanagement.service;


import com.example.productmanagement.dto.ProductDTO;
import com.example.productmanagement.mapper.ProductMapper;
import com.example.productmanagement.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {


    @Autowired
    private  ProductRepository repository;


    @Autowired
    private  ProductMapper mapper;
    public List<ProductDTO> findAllProduct() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

}
