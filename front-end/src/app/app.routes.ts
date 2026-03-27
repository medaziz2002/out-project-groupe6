import { Routes } from '@angular/router';
import { ProductsComponent } from './produits/produits.component';
import { AddProductComponent } from './add-produit/add-produit.component';
import { SearchByCategoryComponent } from './recherche-par-categorie/recherche-par-categorie.component';
import { SearchByNameComponent } from './recherche-par-nom/recherche-par-nom.component';
import { ListCategoriesComponent } from './liste-categories/liste-categories.component';
import { UpdateProductComponent } from './update-product/update-product.component';

export const routes: Routes = [
  { path: '', redirectTo: 'products', pathMatch: 'full' },
  { path: 'products', component: ProductsComponent },
  { path: 'add-product', component: AddProductComponent },
  { path: 'search-by-category', component: SearchByCategoryComponent },
  { path: 'search-by-name', component: SearchByNameComponent },
  { path: 'categories', component: ListCategoriesComponent },
  { path: 'update-product/:id', component: UpdateProductComponent},
];

