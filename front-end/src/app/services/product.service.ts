import { Injectable } from '@angular/core';
import { Category } from '../model/category.model';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Product } from '../model/product.model';
import { Image } from '../model/image.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root',
})
export class ProductService {

  apiURL: string    = 'http://localhost:9191/api/v1/products';
  apiURLCat: string = 'http://localhost:9191/api/v1/categories';

  products!: Product[];

  constructor(private http: HttpClient) { }



  listProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiURL);
  }

  addProduct(prod: Product): Observable<Product> {
    return this.http.post<Product>(this.apiURL, prod, httpOptions);
  }

  deleteProduct(id: number) {
  return this.http.delete(`${this.apiURL}/${id}`);
}


  getProductById(id: number): Observable<Product> {
    return this.http.get<Product>(`${this.apiURL}/${id}`);
  }

  updateProduct(id: number, prod: Product): Observable<Product> {
    return this.http.put<Product>(`${this.apiURL}/${prod.productId}`, prod, httpOptions);
  }

  listCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.apiURLCat);
  }

  searchByCategory(categoryId: number): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiURL}/prodscat/${categoryId}`);
  }

  searchByName(name: string): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiURL}/prodsByName/${name}`);
  }

  addCategory(cat: Category): Observable<Category> {
    return this.http.post<Category>(this.apiURLCat, cat, httpOptions);
  }
getCategoryById(id: number): Observable<Category> {
  return this.http.get<Category>(`${this.apiURLCat}/${id}`);
}

updateCategory(category: Category): Observable<Category> {
  return this.http.put<Category>(`${this.apiURLCat}/${category.categoryId}`, category, httpOptions);
}

deleteCategory(id: number) {
  return this.http.delete(`${this.apiURLCat}/${id}`);
}
  uploadImage(file: File, filename: string) {
    const imageFormData = new FormData();
    imageFormData.append('image', file, filename);
    return this.http.post<Image>(`${this.apiURL}/image/upload`, imageFormData);
  }

  loadImage(id: number): Observable<Image> {
    return this.http.get<Image>(`${this.apiURL}/image/get/info/${id}`);
  }

  uploadImageProd(file: File, filename: string, idProd: number): Observable<any> {
    const imageFormData = new FormData();
    imageFormData.append('image', file, filename);
    return this.http.post(`${this.apiURL}/image/uploadImageProd/${idProd}`, imageFormData);
  }

  deleteImage(id: number) {
    return this.http.delete(`${this.apiURL}/image/delete/${id}`);
  }

  uploadImageFS(file: File, filename: string, idProd: number): Observable<any> {
    const imageFormData = new FormData();
    imageFormData.append('image', file, filename);
    return this.http.post(`${this.apiURL}/image/uploadFS/${idProd}`, imageFormData);
  }
}
