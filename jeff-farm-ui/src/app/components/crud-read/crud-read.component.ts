import { Component, OnInit, Input } from '@angular/core';
import { CrudItem } from 'src/app/classes/crud.item';
import { CrudService } from 'src/app/services/crud.service';

@Component({
  selector: 'app-crud-read',
  templateUrl: './crud-read.component.html',
  styleUrls: ['./crud-read.component.css']
})
export class CrudReadComponent<T extends CrudItem> implements OnInit {

  @Input() crudService: CrudService<T>;
  @Input() itemNames: string[];
  items: T[];

  constructor() { }

  ngOnInit() {
    this.getItems();
  }

  getItems(): void {
    this.crudService.getList()
      .subscribe(items => { this.items = items });
  }

  updateItem(item: T) {
    console.log("TODO: Update farm " + JSON.stringify(item));
  }

  deleteItem(item: T) {
    if (window.confirm('Really Delete?')) {
      this.crudService.delete(item)
        .subscribe(response => { this.getItems() });
    }
  }
}
