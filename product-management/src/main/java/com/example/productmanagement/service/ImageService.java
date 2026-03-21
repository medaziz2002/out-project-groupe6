package com.example.productmanagement.service;


import com.example.productmanagement.entity.Image;
import com.example.productmanagement.entity.Product;
import com.example.productmanagement.repository.ImageRepository;
import com.example.productmanagement.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class ImageService {

   private final ImageRepository imageRepository;



   private  final ProductService productService;


    private  final ProductRepository produitRepository;


    public Image uplaodImage(MultipartFile file) throws IOException {
        /*
         * Ce code commenté est équivalent au code utilisant le design pattern Builder
         * Image image = new Image(null, file.getOriginalFilename(),
         * file.getContentType(), file.getBytes(), null);
         *   return imageRepository.save(image);
         */
        return imageRepository.save(Image.builder().name(file.getOriginalFilename()).type(file.getContentType())
                .image(file.getBytes()).build());
    }


    public Image getImageDetails(Long id) throws IOException {
        final Optional<Image> dbImage = imageRepository.findById(id);
        return Image.builder().imageId(dbImage.get().getImageId()).name(dbImage.get().getName())
                .type(dbImage.get().getType()).image(dbImage.get().getImage()).build();
    }


    public ResponseEntity<byte[]> getImage(Long id) throws IOException {
        final Optional<Image> dbImage = imageRepository.findById(id);
        return ResponseEntity.ok().contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(dbImage.get().getImage());
    }


    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }


    public Image uplaodImageProd(MultipartFile file,Long idProd)
            throws IOException {
        Product p = new Product();
        p.setProductId(idProd);
        return imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(file.getBytes())
                .product(p).build() );
    }


    public List<Image> getImagesParProd(Long prodId) {
        Product p = produitRepository.findById(prodId).get();
        return p.getImages();
    }

}
