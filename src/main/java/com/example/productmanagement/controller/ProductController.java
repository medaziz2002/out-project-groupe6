package com.example.productmanagement.controller;

import com.example.productmanagement.dto.ProductDTO;
import com.example.productmanagement.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {


    @Autowired
    private  ProductService productService;

    @GetMapping
    public List<ProductDTO> getAllProduits() {
        return productService.findAllProduct();
    }




}