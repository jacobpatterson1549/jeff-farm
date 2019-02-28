import { Component, OnInit, Input } from '@angular/core';
import { CrudItem } from '../../classes/crud.item';
import { CrudService } from '../../services/crud.service';

@Component({
  selector: 'app-crud-list',
  templateUrl: './crud-list.component.html',
  styleUrls: ['./crud-list.component.css']
})
export class CrudListComponent<T extends CrudItem> implements OnInit {

  @Input() crudService: CrudService<T>;
  @Input() itemNames: string[];
  @Input() displayField: string;
  items: T[];


  constructor() { }

  ngOnInit() {
    this.getItems();
  }

  getItems(): void {
    this.crudService.getList()
      .subscribe(items => { this.items = items });
  }
}
