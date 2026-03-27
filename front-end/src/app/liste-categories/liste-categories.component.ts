import { Component, OnInit } from '@angular/core';
import { Category } from '../model/category.model';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-list-categories',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './liste-categories.component.html',
})
export class ListCategoriesComponent implements OnInit {
  categories!: Category[];
  selectedCategory: Category = new Category();
  isAdd: boolean = true;

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.loadCategories();
  }

  loadCategories() {
    this.productService.listCategories().subscribe((cats) => {
      this.categories = cats;
    });
  }

  editCategory(cat: Category) {
    this.selectedCategory = { ...cat };
    this.isAdd = false;
  }

  deleteCategory(id: number) {
    if (confirm('Are you sure?')) {
      this.productService.deleteCategory(id).subscribe(() => {
        this.loadCategories();
      });
    }
  }

  saveCategory() {
    if (this.isAdd) {
      this.productService.addCategory(this.selectedCategory).subscribe(() => {
        this.loadCategories();
        this.resetForm();
      });
    } else {
      this.productService.updateCategory(this.selectedCategory).subscribe(() => {
        this.loadCategories();
        this.resetForm();
      });
    }
  }

  resetForm() {
    this.selectedCategory = new Category();
    this.isAdd = true;
  }
}