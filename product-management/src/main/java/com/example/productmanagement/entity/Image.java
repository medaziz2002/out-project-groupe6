package com.example.productmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    private String name;
    private String type;

    @Column(name = "IMAGE", columnDefinition = "bytea")
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}