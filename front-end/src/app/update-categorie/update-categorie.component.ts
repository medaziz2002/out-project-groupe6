import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Category } from '../model/category.model';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-update-category',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './update-categorie.component.html',
  styles: ``
})
export class UpdateCategoryComponent implements OnInit {

  @Input()
  category!: Category;

  @Output()
  categoryUpdated = new EventEmitter<Category>();

  @Input()
  isAdd!: boolean;

  ngOnInit(): void {
    console.log("ngOnInit UpdateCategory ", this.category);
  }

  saveCategory() {
    this.categoryUpdated.emit(this.category);
  }
}