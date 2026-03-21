import { Component, OnInit } from '@angular/core';
import { Product } from '../model/product.model';
import { Category } from '../model/category.model';
import { ProductService } from '../services/product.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-search-by-category',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './recherche-par-categorie.component.html',
  styles: ``
})
export class SearchByCategoryComponent implements OnInit {
  products!: Product[];
  selectedCategoryId!: number;
  categories!: Category[];

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.productService.listCategories().subscribe((cats) => {
      this.categories = cats;
    });
  }

  onChange() {
    this.productService.searchByCategory(this.selectedCategoryId)
      .subscribe((prods) => { this.products = prods; });
  }
}