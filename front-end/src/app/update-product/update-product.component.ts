import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import {  Router ,ActivatedRoute } from '@angular/router';


import { Category } from '../model/category.model';
import { Product } from '../model/product.model';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-update-product',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './update-product.component.html',
})
export class UpdateProductComponent implements OnInit {
  product = new Product();
  categories!: Category[];
  selectedCategoryId!: number;
  uploadedImage!: File;
  imagePath: any;

  constructor(private productService: ProductService, private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {

    this.productService.listCategories().subscribe((cats) => {
      this.categories = cats;
    });

const id = Number(this.route.snapshot.params['id']);
  if (id) {

    this.productService.getProductById(id).subscribe((prod: Product) => {
      console.log("PRODUCT =", prod);
      this.product = prod;
      this.selectedCategoryId = prod.categoryId;
     // this.imagePath = prod.imagePath;
    });
  }

}

updateProduct() {
  const selectedCat = this.categories.find(
    (cat) => cat.categoryId == this.selectedCategoryId
  );

  if (selectedCat) {
    this.product.categoryId = selectedCat.categoryId;
  }

    this.productService.updateProduct(this.product.productId, this.product).subscribe(() => {
        if (this.uploadedImage) {
             this.productService
               .uploadImageFS(this.uploadedImage, this.uploadedImage.name, this.product.productId)
               .subscribe((response: any) => {});
           }
          this.router.navigate(['products']);
      });
}

  onImageUpload(event: any) {
    this.uploadedImage = event.target.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(this.uploadedImage);
    reader.onload = (_event) => {
      this.imagePath = reader.result;
    };
  }
}
