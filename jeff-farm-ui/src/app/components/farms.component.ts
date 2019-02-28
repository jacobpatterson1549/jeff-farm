import { Component } from '@angular/core';

import { FarmsService } from '../services/farms.service';
import { FormType } from '../classes/form.type';
import { Farm } from '../classes/farm';

@Component({
  selector: 'app-list-farms',
  template: '<app-crud-list [crudService]="farmService" [displayField]="displayField"></app-crud-list>'
})
export class FarmsListComponent {

  displayField: string = 'name';
  
  constructor(private farmService: FarmsService) { }
}

@Component({
  selector: 'app-farms-view',
  template: '<app-crud-view [crudService]="farmService" [itemNames]="itemNames" [children]="children"></app-crud-view>'
})
export class FarmsViewComponent {

  itemNames: string[] = Farm.ITEM_NAMES;
  children: string[] = ['hives'];
  
  constructor(private farmService: FarmsService) { }
}

@Component({
  selector: 'app-farms-create',
  template: '<app-crud-form [crudService]="farmService" [formType]="formType" [crudItem]="crudItem"></app-crud-form>'
})
export class FarmsCreateComponent {

  formType = FormType.Create;
  crudItem: Farm = new Farm();
  
  constructor(private farmService: FarmsService) { }
}

@Component({
  selector: 'app-farms-update',
  template: '<app-crud-form [crudService]="farmService" [formType]="formType" [crudItem]="crudItem"></app-crud-form>'
})
export class FarmsUpdateComponent {

  formType = FormType.Update;
  crudItem: Farm = new Farm();
    
  constructor(private farmService: FarmsService) { }
}
