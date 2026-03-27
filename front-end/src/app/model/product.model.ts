import { Category } from "./category.model";
import { Image } from "./image.model";


export class Product {
  productId!: number;
  productName!: string;
  productPrice!: number;
  creationDate!: Date;
  categoryId!: number;  
  categoryDTO!: Category;
  image! : Image;
  imageStr!:string;
  images!: Image[];
  }