import * as Highcharts from 'highcharts';

import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { CrudItem } from '../crud-item';
import { CrudItemCoordinate } from '../crud-item-coordinate';
import { CrudItemMap } from '../crud-item-map';
import { CrudItemMapService } from '../crud-item-map-service';

export interface ChartTarget {
  id: number;
  name: string;
}

@Component({
  selector: 'app-crud-chart2',
  templateUrl: './crud-chart2.component.html',
})
export class CrudChart2Component<T extends CrudItem> implements OnInit {

  @Input()
  crudItems: T[];
  chartTargets: ChartTarget[];
  public options = {
    title: {
      text: 'Coordinate Map',
    },
    tooltip: {
      formatter() {
        return `<strong>${this.series.name}</strong>`
          + `<br/>Latitude: ${this.y}<br/>Longitude: ${this.x}`;
      }
    },
    legend: {
      enabled: true
    },
    yAxis: {
      title: {
        text: 'latitude',
      }
    },
    xAxis: {
      title: {
        text: 'longitude',
      }
    },
    plotOptions: {
      series: {
        point: {
          events: {
            click() {
              location.href = `${location.href}/${this.series.options.url}`;
            }
          }
        }
      },
      scatter: {
        lineWidth: 2,
        allowPointSelect: false,
      },
    },
    series: [],
  };

  // TODO: Make CrudChartService (interface) and have CrudItemInspectionGroupService
  //       and CrudItemMapService extend it.
  constructor(
    private crudItemMapService: CrudItemMapService,
    route: ActivatedRoute) {
    this.crudItemMapService.setRoute(route);
  }

  ngOnInit() {
    this.crudItemMapService.getTargets()
      .subscribe(targets => {
        this.chartTargets = Object.keys(targets)
          .map(targetId => {
            return { id: +targetId, name: targets[targetId] } as ChartTarget;
          });
        this.setChartTarget(0);
      });
  }

  setChartTarget(index: number) {
    const chartTarget = this.chartTargets[index];
    this.options.series = this.crudItems
      .filter((t: T) => t instanceof CrudItemMap && t.targetId === chartTarget.id)
      .map((t: T) => {
        const crudItemMap = t instanceof CrudItemMap ? t : null;
        return {
          type: 'scatter',
          name: crudItemMap.createdDate,
          data: this.getMapData(crudItemMap.coordinates),
          url: crudItemMap.id,
        };
      });
    Highcharts.chart('chart', this.options); // Plot the data to the "chart" div.
  }

  private getMapData(coordinates: CrudItemCoordinate[]): number[][] {
    const data: number[][] = coordinates
      .map((coordinate: CrudItemCoordinate) => [
        coordinate.longitude,
        coordinate.latitude,
      ]);
    if (data.length > 1) {
      data.push(data[0]); // HACK: Add the first point to the end so the points are connected.
    }
    return data;
  }
}
