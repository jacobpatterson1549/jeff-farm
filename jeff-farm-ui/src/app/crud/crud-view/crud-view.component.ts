import { Component, OnInit } from '@angular/core';

import { CrudService } from '../crud.service';
import { NavigationService } from '../../navigation.service';

import { CrudItem } from '../crud.item';

@Component({
  selector: 'crud-view',
  templateUrl: './crud-view.component.html',
  styleUrls: ['./crud-view.component.css']
})
export class CrudViewComponent<T extends CrudItem> implements OnInit {

  crudItem: T;
  canDelete: boolean = false;
  childNames: string[];

  constructor(
    private crudService: CrudService<T>,
    private navigationService: NavigationService) { }

  ngOnInit() {
    this.childNames = this.crudService.getChildNames();
    
    this.crudService.get()
      .subscribe(crudItem => this.crudItem = crudItem);
    
    this.crudService.canDelete()
      .subscribe(canDelete => this.canDelete = canDelete);
  }

  deleteCrudItem() {
    if (window.confirm('Really Delete?')) {
      this.crudService.delete()
        .subscribe(result => this.navigationService.goBack() );
    }
  }
}
