import { Component, OnInit } from '@angular/core';

import { Farm } from '../../classes/farm';
import { FarmService } from '../../services/farm.service';

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

  handleTabClick(tab: string) {
    console.log(tab + ' tab clicked.');
  }

  getFarms(): void {
    this.farmService.getList()
      .subscribe(farms => this.farms = farms);
  }

  openUpdateTab(farm: Farm) {
    console.log("TODO: Update farm " + JSON.stringify(farm));
  }

  deleteFarm(farm: Farm) {
    console.log("TODO: Delete farm " + JSON.stringify(farm));
  }
}
