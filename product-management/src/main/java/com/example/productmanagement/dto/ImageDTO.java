package com.example.productmanagement.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDTO {

    private Long imageId;
    private String name;
    private String type;
    private byte[] image;
    private Long productId;

}