import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Product } from '../model/product.model';
import { RouterLink } from '@angular/router';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule],
  templateUrl: 'produits.component.html',
})
export class ProductsComponent implements OnInit {
  products: Product[] = [];
  apiurl: string = 'http://localhost:9191/api/v1/products';

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts() {
    this.productService.listProducts().subscribe((prods) => {
      this.products = prods;
    });
  }

  deleteProduct(prod: Product) {
  if (confirm("Are you sure you want to delete this product?")) {
    this.productService.deleteProduct(prod.productId).subscribe(() => {
      this.loadProducts(); 
    });
  }
}



}