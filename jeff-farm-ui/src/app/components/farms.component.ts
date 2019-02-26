import { Component, OnInit } from '@angular/core';

import { FarmService } from '../services/farm.service';
import { FormType } from '../classes/form.type';

@Component({
  selector: 'app-list-farms',
  template: '<app-crud-read [crudService]="farmService" [itemNames]="itemNames"></app-crud-read>'
})
export class FarmsListComponent implements OnInit {

  itemNames = ['name', 'location'];
  
  constructor(private farmService: FarmService) { }

  ngOnInit() { }
}

@Component({
  selector: 'app-farms-create',
  template: '<app-crud-form [crudService]="farmService" [formType]="formType"></app-crud-form>'
})
export class FarmsCreateComponent implements OnInit {

  formType = FormType.Create;
  
  constructor(private farmService: FarmService) { }

  ngOnInit() { }
}

@Component({
  selector: 'app-farms-update',
  template: '<app-crud-form [crudService]="farmService" [formType]="formType"></app-crud-form>'
})
export class FarmsUpdateComponent implements OnInit {

  formType = FormType.Update;
  
  constructor(private farmService: FarmService) { }

  ngOnInit() { }
}
