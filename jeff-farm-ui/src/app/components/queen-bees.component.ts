import { Component } from '@angular/core';

import { QueenBeesService } from '../services/queenBees.service';
import { FormType } from '../classes/form.type';
import { QueenBee } from '../classes/queenBee';

@Component({
  selector: 'app-list-queenBees',
  template: '<app-crud-list [crudService]="queenBeeService" [displayField]="displayField"></app-crud-list>'
})
export class QueenBeesListComponent {

  displayField: string = 'mark_color';
  
  constructor(private queenBeeService: QueenBeesService) { }
}

@Component({
  selector: 'app-queenBees-view',
  template: '<app-crud-view [crudService]="queenBeeService" [itemNames]="itemNames"></app-crud-view>'
})
export class QueenBeesViewComponent {

  itemNames: string[] = QueenBee.ITEM_NAMES;
  
  constructor(private queenBeeService: QueenBeesService) { }
}

@Component({
  selector: 'app-queenBees-create',
  template: '<app-crud-form [crudService]="queenBeeService" [formType]="formType" [crudItem]="crudItem"></app-crud-form>'
})
export class QueenBeesCreateComponent {

  formType = FormType.Create;
  crudItem: QueenBee = new QueenBee();
  
  constructor(private queenBeeService: QueenBeesService) { }
}

@Component({
  selector: 'app-queenBees-update',
  template: '<app-crud-form [crudService]="queenBeeService" [formType]="formType" [crudItem]="crudItem"></app-crud-form>'
})
export class QueenBeesUpdateComponent {

  formType = FormType.Update;
  crudItem: QueenBee = new QueenBee();
    
  constructor(private queenBeeService: QueenBeesService) { }
}
