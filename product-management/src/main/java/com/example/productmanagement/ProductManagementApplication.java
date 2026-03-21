package com.example.productmanagement;

import com.example.productmanagement.entity.Category;
import com.example.productmanagement.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ProductManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductManagementApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(CategoryRepository categoryRepository) {
		return args -> {
			if (categoryRepository.count() == 0) {
				categoryRepository.saveAll(List.of(
						Category.builder().categoryName("Electronics").categoryDescription("Electronic devices and accessories").build(),
						Category.builder().categoryName("Computers").categoryDescription("Laptops, desktops and computer accessories").build(),
						Category.builder().categoryName("Smartphones").categoryDescription("Mobile phones and tablets").build(),
						Category.builder().categoryName("Printers").categoryDescription("Printers, scanners and ink cartridges").build(),
						Category.builder().categoryName("Accessories").categoryDescription("Cables, chargers and other accessories").build()
				));
				System.out.println("5 categories inserted successfully!");
			}
		};
	}
}