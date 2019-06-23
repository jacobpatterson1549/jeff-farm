import * as Highcharts from 'highcharts';

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { CrudItem } from '../crud-item';
import { CrudItemInspection } from '../crud-item-inspection';
import { CrudItemInspectionGroup } from '../crud-item-inspection-group';
import { CrudItemInspectionGroupService } from '../crud-item-inspection-group.service';
import { FormItem } from '../form-item';

@Component({
  selector: 'app-crud-chart',
  templateUrl: './crud-chart.component.html',
})
export class CrudChartComponent
  <U extends CrudItem,
  V extends CrudItemInspection<U>,
  T extends CrudItemInspectionGroup<V>>
  implements OnInit {

  public options: any = {
    chart: {
      type: 'column',
      height: 700
    },
    title: {},
    credits: {
      enabled: false
    },
    tooltip: {
      formatter() {
        return `x: ${Highcharts.dateFormat('%e %b %y %H:%M:%S', this.x)} y: ${this.y.toFixed(2)}`;
      }
    },
    xAxis: {
      categories: [],
      crosshair: true
    },
    yAxis: {
      title: {}
    },
    series: []
  };
  private formItems: FormItem[];
  private groups: T[];

  constructor(
    route: ActivatedRoute,
    private crudItemInspectionGroupService: CrudItemInspectionGroupService<U, V, T>) {
    this.crudItemInspectionGroupService.setRoute(route);
  }

  ngOnInit() {
    this.options.title.text
      = `${this.crudItemInspectionGroupService.getTypeName()} chart`;
    this.formItems = this.crudItemInspectionGroupService.createCrudItemInspection()
      .getFormItems();
    this.crudItemInspectionGroupService.getList()
      .subscribe((groups: T[]) => {
        this.groups = groups;
        this.options.xAxis.categories = groups.map(group => group.createdDate);
        this.chartFormItem(0);
      });
    this.crudItemInspectionGroupService.getTargets()
      .subscribe((targets: Map<number, string>) => {
        this.options.series = Object.values(targets).map(targetName => {
          return {
            name: targetName,
            data: [],
          };
        });
        this.chartFormItem(0);
      });
  }

  private chartFormItem(index: number) {
    if (this.groups == null || !this.options.series.length) {
      return;
    }

    // reset the series
    this.options.series.forEach(s => s.data = Array(this.groups.length).fill(''));

    // add data to the empty series
    const formItemName: string = this.formItems[index].name;
    this.options.yAxis.title.text = formItemName;
    for (let x = 0; x < this.groups.length; x++) {
      const group = this.groups[x];
      for (const inspectionItem of group.inspectionItems) {
        const seriesIndex = this.options.series
        .findIndex(s => s.name === inspectionItem.targetName);
        if (seriesIndex >= 0) {
          this.options.series[seriesIndex].data[x] = inspectionItem[formItemName];
        }
      }
    }

    Highcharts.chart('chart', this.options); // plot the data
  }
}
