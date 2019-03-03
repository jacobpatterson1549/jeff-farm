import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { CrudItem } from '../../classes/crud.item';
import { CrudService } from '../../services/crud.service';
import { NavigationComponent } from '../navigation.component';

@Component({
  providers: [NavigationComponent],
  selector: 'app-crud-view',
  templateUrl: './crud-view.component.html',
  styleUrls: ['./crud-view.component.css']
})
export class CrudViewComponent<T extends CrudItem> implements OnInit {

  @Input() crudService: CrudService<T>;
  @Input() itemNames: string[];
  @Input() children: string[];
  item: T;
  canDelete: boolean = false;

  constructor(
    private navigationComponent: NavigationComponent) { }

  ngOnInit() {
    this.crudService.get()
      .subscribe(item => this.item = item);
    
    this.crudService.canDelete()
      .subscribe(canDelete => this.canDelete = canDelete);
  }

  deleteItem() {
    if (window.confirm('Really Delete?')) {
      this.crudService.delete()
        .subscribe(result => this.navigationComponent.goBack() );
    }
  }
}
