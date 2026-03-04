package com.example.productmanagement.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private Double productPrice;
    private Date creationDate;

    @ManyToOne
    private Category category;


    @OneToMany(mappedBy = "product")
    private List<Image> images;



}
