import { Component, OnInit } from '@angular/core';

import { FarmService } from '../services/farm.service';

@Component({
  selector: 'app-farms',
  template: '<app-crud-read [crudService]="farmService" [itemNames]="itemNames"></app-crud-read>'
})
export class FarmsComponent implements OnInit {

  itemNames = ['name', 'location'];
  
  constructor(private farmService: FarmService) { }

  ngOnInit() { }
}
