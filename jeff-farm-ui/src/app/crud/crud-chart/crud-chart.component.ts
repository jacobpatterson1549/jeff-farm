import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { CrudItem } from '../crud-item';
import { CrudItemInspection } from '../crud-item-inspection';
import { CrudItemInspectionGroup } from '../crud-item-inspection-group';
import { CrudItemInspectionGroupService } from '../crud-item-inspection-group.service';

@Component({
  selector: 'app-crud-chart',
  templateUrl: './crud-chart.component.html',
})
export class CrudChartComponent
  <U extends CrudItem,
  V extends CrudItemInspection<U>,
  T extends CrudItemInspectionGroup<V>>
  implements OnInit {

  groups: T[];

  constructor(
    route: ActivatedRoute,
    private crudItemInspectionGroupService: CrudItemInspectionGroupService<U, V, T>) {
    this.crudItemInspectionGroupService.setRoute(route);
  }

  ngOnInit() {
    this.crudItemInspectionGroupService.getList()
      .subscribe((groups: T[]) => {
        this.groups = groups;
      });
  }
}
