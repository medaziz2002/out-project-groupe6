import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Category } from '../model/category.model';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Product } from '../model/product.model';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-add-product',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './add-produit.component.html',
})
export class AddProductComponent implements OnInit {
  newProduct = new Product();
  categories!: Category[];
  selectedCategoryId!: number;
  selectedCategory!: Category;

  uploadedImage!: File;
  imagePath: any;

  constructor(private productService: ProductService, private router: Router) {}

  ngOnInit(): void {
    this.productService.listCategories().subscribe((cats) => {
      this.categories = cats;
    });
  }

  /*addProduct(){
    this.newProduct.category = this.categories.find(cat => cat.categoryId == this.selectedCategoryId)!;
    this.productService.addProduct(this.newProduct)
                      .subscribe(prod => {
                      console.log(prod);
                      this.router.navigate(['products']);
                      }); 
    }*/

  /* addProduct() {
    this.productService
      .uploadImage(this.uploadedImage, this.uploadedImage.name)
      .subscribe((img: Image) => {
        this.newProduct.image = img;
        this.newProduct.category = this.categories.find(cat => cat.categoryId == this.selectedCategoryId)!;
        
        this.productService
          .addProduct(this.newProduct)
          .subscribe(() => {
            this.router.navigate(['products']);
          });
      });
  }*/

  /* addProduct() {
    this.productService
      .uploadImage(this.uploadedImage, this.uploadedImage.name)
      .subscribe((img: Image) => {
        console.log("immmmmmmmmmmm "+img.idImage);
        this.newProduct.image = img;
        this.newProduct.category = this.categories.find(
          (cat) => cat.categoryId == this.selectedCategoryId
        )!;
        this.productService.addProduct(this.newProduct).subscribe(() => {
          this.router.navigate(['products']);
        });
      }); */

addProduct() {
  const selectedCat = this.categories.find(
    (cat) => cat.categoryId == this.selectedCategoryId
  );

  if (selectedCat) {
    this.newProduct.categoryDTO = selectedCat;
    this.newProduct.categoryId = selectedCat.categoryId;  // ajouter aussi categoryId
  }

  this.productService.addProduct(this.newProduct).subscribe((prod) => {
    if (this.uploadedImage) {
      this.productService
        .uploadImageFS(this.uploadedImage, this.uploadedImage.name, prod.productId)
        .subscribe((response: any) => {});
    }
    this.router.navigate(['products']);
  });
}

  /*
    this.newProduct.category = this.categories.find(cat => cat.categoryId == this.selectedCategoryId)!;
    this.productService
      .addProduct(this.newProduct)
      .subscribe((prod) => {
        this.productService
          .uploadImageFS(this.uploadedImage,
            this.uploadedImage.name, prod.productId)
          .subscribe((response: any) => { }
          );
        this.router.navigate(['products']);
      }); */

  onImageUpload(event: any) {
    this.uploadedImage = event.target.files[0];
    var reader = new FileReader();
    reader.readAsDataURL(this.uploadedImage);
    reader.onload = (_event) => {
      this.imagePath = reader.result;
    };
  }
}