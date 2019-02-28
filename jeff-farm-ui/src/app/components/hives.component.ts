import { Component } from '@angular/core';

import { HivesService } from '../services/hives.service';
import { FormType } from '../classes/form.type';
import { Hive } from '../classes/hive';

@Component({
  providers: [HivesService],
  selector: 'app-list-farms',
  template: '<app-crud-list [crudService]="hivesService" [displayField]="displayField"></app-crud-list>'
})
export class HivesListComponent {

  displayField: string = 'createdDate';
  
  constructor(private hivesService: HivesService) { }
}

@Component({
  providers: [HivesService],
  selector: 'app-farms-view',
  template: '<app-crud-view [crudService]="hivesService" [itemNames]="itemNames" [children]="children"></app-crud-view>'
})
export class HivesViewComponent {

  itemNames: string[] = Hive.ITEM_NAMES;
  children: ['hives'];
  
  constructor(private hivesService: HivesService) { }
}

@Component({
  providers: [HivesService],
  selector: 'app-farms-create',
  template: '<app-crud-form [crudService]="hivesService" [formType]="formType" [crudItem]="crudItem"></app-crud-form>'
})
export class HivesCreateComponent {

  formType = FormType.Create;
  crudItem: Hive = new Hive(this.hivesService.getFarmId());
  
  constructor(private hivesService: HivesService) { }
}

@Component({
  selector: 'app-farms-update',
  template: '<app-crud-form [crudService]="hivesService" [formType]="formType" [crudItem]="crudItem"></app-crud-form>'
})
export class HivesUpdateComponent {

  formType = FormType.Update;
  crudItem: Hive = new Hive(this.hivesService.getFarmId());
    
  constructor(private hivesService: HivesService) { }
}
