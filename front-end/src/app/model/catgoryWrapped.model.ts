import { Category } from './category.model';
export class CategorieWrapper{
    _embedded!: { categories: Category[]};
}
