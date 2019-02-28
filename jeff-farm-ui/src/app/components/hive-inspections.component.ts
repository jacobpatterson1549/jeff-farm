import { Component } from '@angular/core';

import { HiveInspectionsService } from '../services/hiveInspections.service';
import { FormType } from '../classes/form.type';
import { HiveInspection } from '../classes/hiveInspection';

@Component({
  selector: 'app-list-hiveInspections',
  template: '<app-crud-list [crudService]="hiveInspectionsService" [displayField]="displayField"></app-crud-list>'
})
export class HiveInspectionsListComponent {

  displayField: string = 'name';
  
  constructor(private hiveInspectionsService: HiveInspectionsService) { }
}

@Component({
  selector: 'app-hiveInspections-view',
  template: '<app-crud-view [crudService]="hiveInspectionsService" [itemNames]="itemNames"></app-crud-view>'
})
export class HiveInspectionsViewComponent {

  itemNames: string[] = HiveInspection.ITEM_NAMES;
  
  constructor(private hiveInspectionsService: HiveInspectionsService) { }
}

@Component({
  selector: 'app-hiveInspections-create',
  template: '<app-crud-form [crudService]="hiveInspectionsService" [formType]="formType" [crudItem]="crudItem"></app-crud-form>'
})
export class HiveInspectionsCreateComponent {

  formType = FormType.Create;
  crudItem: HiveInspection = new HiveInspection();
  
  constructor(private hiveInspectionsService: HiveInspectionsService) { }
}

@Component({
  selector: 'app-hiveInspections-update',
  template: '<app-crud-form [crudService]="hiveInspectionsService" [formType]="formType" [crudItem]="crudItem"></app-crud-form>'
})
export class HiveInspectionsUpdateComponent {

  formType = FormType.Update;
  crudItem: HiveInspection = new HiveInspection();
    
  constructor(private hiveInspectionsService: HiveInspectionsService) { }
}
