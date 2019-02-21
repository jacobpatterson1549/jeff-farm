import { Component, OnInit } from '@angular/core';

import { Farm } from '../classes/farm';
import { FarmService } from '../farm.service';

@Component({
  selector: 'app-farms',
  templateUrl: './farms.component.html',
  styleUrls: ['./farms.component.css']
})
export class FarmsComponent implements OnInit {

  farms: Farm[];
  tabs: ["List", "Create"];
  selectedTab: "List";

  constructor(private farmService: FarmService) { }

  ngOnInit() {
    this.getFarms();
  }

  handleTabClick(tab) {
    console.log(tab + ' tab clicked.');
  }

  getFarms(): void {
    this.farmService.getFarms()
      .subscribe(farms => this.farms = farms);
  }

  openUpdateTab(farm) {
    console.log("TODO: Update farm " + farm);
  }

  deleteFarm(farm) {
    console.log("TODO: Delete farm " + farm);
  }
}
