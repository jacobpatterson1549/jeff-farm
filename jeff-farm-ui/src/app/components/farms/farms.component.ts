import { Component, OnInit } from '@angular/core';

import { FarmService } from '../../services/farm.service';

@Component({
  selector: 'app-farms',
  templateUrl: './farms.component.html',
  styleUrls: ['./farms.component.css']
})
export class FarmsComponent implements OnInit {

  itemNames = ['name', 'location'];
  
  constructor(private farmService: FarmService) { }

  ngOnInit() { }
}
