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
      // type: 'datetime',
      categories: [],
      crosshair: true
    },
    yAxis: {
      title: { }
    },
    series: []
  };
  private formItems: FormItem[];
  private groups: T[];
  private targetNames: string[];

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
        this.targetNames = Object.values(targets);
        this.chartFormItem(0);
      });
  }

  private chartFormItem(index: number) {
    if (this.groups == null || this.targetNames == null) {
      return;
    }

    // reset the series
    const series = [];
    for (const targetName of this.targetNames) {
      series.push({
        name: targetName,
        data: Array(this.groups.length).fill(''),
      });
    }

    // add data to the empty series
    const formItemName: string = this.formItems[index].name;
    for (let x = 0; x < this.groups.length; x++) {
      const group = this.groups[x];
      for (const inspectionItem of group.inspectionItems) {
        const seriesIndex = series.findIndex(s => s.name === inspectionItem.targetName);
        if (seriesIndex >= 0) {
          series[seriesIndex].data[x] = inspectionItem[formItemName];
        }
      }
    }

    this.options.yAxis.title.text = formItemName;
    this.options.series = series;
    Highcharts.chart('chart', this.options); // plot the data
  }
}
