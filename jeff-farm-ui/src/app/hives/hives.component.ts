import { Component } from '@angular/core';
import { Hive } from './hive';
import { HivesService } from './hives.service';


@Component({
  providers: [HivesService],
  selector: 'app-hives',
  template: '<p>hives</p>'
  // template: `<crud-home
  //   [crudService]="hiveService"
  //   [crudItem]="crudItem"
  //   [crudType]="crudType"
  //   [displayField]="displayField"
  //   [itemNames]="itemNames"
  //   [childNames]="childNames"
  //   ></crud-home>`
})
export class HivesComponent {


  constructor(private hiveService: HivesService) { }
}
