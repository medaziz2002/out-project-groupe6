import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Product } from '../model/product.model';
import { ProductService } from '../services/product.service';
import { SearchFilterPipe } from '../search-filter.pipe';

@Component({
  selector: 'app-search-by-name',
  standalone: true,
  imports: [FormsModule, CommonModule, SearchFilterPipe],
  templateUrl: './recherche-par-nom.component.html',
})
export class SearchByNameComponent implements OnInit {
 productName!: string;
  products!: Product[];
  allProducts!: Product[];
  searchTerm!: string;

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.productService.listProducts().subscribe(prods => {
      console.log(prods);
      this.products = prods;
      this.allProducts = prods; // ← initialisation de allProducts
    });
  }

    searchProducts(): void {
    if (this.productName === '' || !this.productName) {
      this.ngOnInit();
    } else {
      this.productService.searchByName(this.productName).subscribe(prods => {
        console.log(prods);
        this.products = prods;
      });
    }
  }

  onKeyUp(filterText: string): void {
    this.products = this.allProducts.filter(item =>
      item.productName?.toLowerCase().includes(filterText.toLowerCase()) 
    );
  }

    
}
