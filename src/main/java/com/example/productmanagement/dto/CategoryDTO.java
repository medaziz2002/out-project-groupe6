package com.example.productmanagement.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class CategoryDTO {

    private Long categoryId;
    private String categoryName;
    private String categoryDescription;
    private List<Long> productIds;

}
