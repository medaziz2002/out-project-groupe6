package com.example.productmanagement.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CategoryDTO {
    private Long categoryId;
    private String categoryName;
    private String categoryDescription;
    private List<Long> productIds;
}