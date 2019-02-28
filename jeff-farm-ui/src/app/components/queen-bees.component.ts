import { Component } from '@angular/core';

import { QueenBeesService } from '../services/queenBees.service';
import { FormType } from '../classes/form.type';
import { QueenBee } from '../classes/queenBee';

@Component({
  providers: [QueenBeesService],
  selector: 'app-queenBees-list',
  template: '<app-crud-list [crudService]="queenBeeService" [displayField]="displayField"></app-crud-list>'
})
export class QueenBeesListComponent {

  displayField: string = 'mark_color';
  
  constructor(private queenBeeService: QueenBeesService) { }
}

@Component({
  providers: [QueenBeesService],
  selector: 'app-queenBees-view',
  template: '<app-crud-view [crudService]="queenBeeService" [itemNames]="itemNames"></app-crud-view>'
})
export class QueenBeesViewComponent {

  itemNames: string[] = QueenBee.ITEM_NAMES;
  
  constructor(private queenBeeService: QueenBeesService) { }
}

@Component({
  providers: [QueenBeesService],
  selector: 'app-queenBees-create',
  template: '<app-crud-form [crudService]="queenBeeService" [formType]="formType" [crudItem]="crudItem"></app-crud-form>'
})
export class QueenBeesCreateComponent {

  formType = FormType.Create;
  crudItem: QueenBee = new QueenBee(this.queenBeeService.getHiveId());
  
  constructor(private queenBeeService: QueenBeesService) { }
}

@Component({
  providers: [QueenBeesService],
  selector: 'app-queenBees-update',
  template: '<app-crud-form [crudService]="queenBeeService" [formType]="formType" [crudItem]="crudItem"></app-crud-form>'
})
export class QueenBeesUpdateComponent {

  formType = FormType.Update;
  crudItem: QueenBee = new QueenBee(this.queenBeeService.getHiveId());
    
  constructor(private queenBeeService: QueenBeesService) { }
}
