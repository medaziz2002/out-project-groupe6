package com.example.productmanagement.dto;


import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProductDTO {

    private Long productId;
    private String productName;
    private Double productPrice;
    private Date creationDate;
    private CategoryDTO categoryDTO;
    private Long categoryId;
    private List<ImageDTO> images;

}
