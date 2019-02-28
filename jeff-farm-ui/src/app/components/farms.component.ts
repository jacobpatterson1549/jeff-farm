import { Component, OnInit } from '@angular/core';

import { FarmService } from '../services/farm.service';
import { FormType } from '../classes/form.type';
import { Farm } from '../classes/farm';

@Component({
  selector: 'app-list-farms',
  template: '<app-crud-list [crudService]="farmService" [displayField]="displayField"></app-crud-list>'
})
export class FarmsListComponent implements OnInit {

  displayField: string = 'name';
  
  constructor(private farmService: FarmService) { }

  ngOnInit() { }
}

@Component({
  selector: 'app-farms-view',
  template: '<app-crud-view [crudService]="farmService" [itemNames]="itemNames"></app-crud-view>'
})
export class FarmsViewComponent implements OnInit {

  itemNames: string[] = Farm.ITEM_NAMES;
  
  constructor(private farmService: FarmService) { }

  ngOnInit() { }
}

@Component({
  selector: 'app-farms-create',
  template: '<app-crud-form [crudService]="farmService" [formType]="formType" [crudItem]="crudItem"></app-crud-form>'
})
export class FarmsCreateComponent implements OnInit {

  formType = FormType.Create;
  crudItem: Farm = new Farm();
  
  constructor(private farmService: FarmService) { }

  ngOnInit() { }
}

@Component({
  selector: 'app-farms-update',
  template: '<app-crud-form [crudService]="farmService" [formType]="formType" [crudItem]="crudItem"></app-crud-form>'
})
export class FarmsUpdateComponent implements OnInit {

  formType = FormType.Update;
  crudItem: Farm = new Farm();
    
  constructor(private farmService: FarmService) { }

  ngOnInit() { }
}
