package com.example.productmanagement.repository;

import com.example.productmanagement.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

}