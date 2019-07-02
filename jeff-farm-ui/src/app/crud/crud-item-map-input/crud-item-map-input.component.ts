import * as Highcharts from 'highcharts';
import DP_Highcharts from 'highcharts/modules/draggable-points';

import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

import { CrudItemCoordinate } from '../crud-item-coordinate';
import { CrudItemMapService } from '../crud-item-map-service';
import { FormItem, FormItemType } from '../form-item';

@Component({
  selector: 'app-crud-item-map-input',
  templateUrl: './crud-item-map-input.component.html',
})
export class CrudItemMapInputComponent implements OnInit {

  @Input()
  coordinates: FormArray;
  crudForm: FormGroup;
  formItems: FormItem[];
  selectTargets: object = {};
  objectKeys = Object.keys; // used in the template
  private canAddCoordinate = false;
  public options = {
    chart: {
      animation: false,
    },
    title: {
      text: 'Coordinate Map',
    },
    tooltip: {
      formatter() {
        return `Latitude: ${this.y}<br/>Longitude: ${this.x}`;
      }
    },
    legend: {
      enabled: false
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
      scatter: {
        lineWidth: 2,
        allowPointSelect: false,
      },
      series: {
        point: {
          events: {
            drop: null,
          }
        }
      },
    },
    series: [{
      type: 'scatter',
      cursor: null,
      dragDrop: {
        draggableX: false,
        draggableY: false,
      },
      data: []
    } as Highcharts.SeriesScatterOptions],
  };

  constructor(
    route: ActivatedRoute,
    private fb: FormBuilder,
    private crudItemMapService: CrudItemMapService) {
    this.crudItemMapService.setRoute(route);
  }

  ngOnInit() {
    this.crudForm = this.coordinates.parent as FormGroup;
    this.formItems = this.crudItemMapService.createCrudItem().getFormItems();
    this.crudItemMapService.getTargets()
      .subscribe(targets => {
        this.selectTargets = targets;
      });
    this.canAddCoordinate = 'geolocation' in navigator; // (also must be https)
    this.initChart();
  }

  private initChart() {
    DP_Highcharts(Highcharts); // load the draggable points module
    const series = this.options.series[0];
    if (this.coordinates.enabled) {
      this.options.title.text = 'Editable ' + this.options.title.text;
      // TODO: select propert coordinate in list :
      // this.options.plotOptions.scatter.allowPointSelect = true;
      this.options.plotOptions.series.point.events.drop = (event) => {
        this.dropCoordinate(event.target.index, event.newPoint.y, event.newPoint.x);
      };
      series.cursor = 'move';
      series.dragDrop.draggableX = true;
      series.dragDrop.draggableY = true;

    }
    this.updateChart();
  }

  updateChart() {
    const data = this.options.series[0].data;
    data.length = 0;
    for (const coordinate of this.coordinates.controls) {
      data.push([
        coordinate.get('longitude').value,
        coordinate.get('latitude').value,
      ]);
    }
    if (data.length > 1) {
      data.push(data[0]); // HACK: Add the first point to the end so the points are connected.
    }

    Highcharts.chart('chart', this.options); // Plot the data to the "chart" div.
  }

  setTarget(targetIndex: number) {
    const targetId = this.objectKeys(this.selectTargets)[targetIndex];
    const targetName = this.selectTargets[targetId];
    this.crudForm.get('targetId').setValue(targetId);
    this.crudForm.get('targetName').setValue(targetName);
  }

  addCoordinate() {
    if (this.canAddCoordinate) {
      navigator.geolocation.getCurrentPosition((position: Position) => {
        const coordinate = new CrudItemCoordinate(this.crudForm.get('id').value);
        coordinate.latitude = position.coords.latitude;
        coordinate.longitude = position.coords.longitude;
        coordinate.displayOrder = this.coordinates.length;
        this.coordinates.push(coordinate.getFormGroup(this.fb));
        this.updateChart();
      });
    }
  }

  moveCoordinateUp(index: number) {
    this.swapCoordinates(index, -1);
  }

  moveCoordinateDown(index: number) {
    this.swapCoordinates(index, +1);
  }

  private swapCoordinates(index: number, delta: number) {
    const coordinateA = this.coordinates.at(index);
    const coordinateB = this.coordinates.at(index + delta);
    coordinateA.get('displayOrder').setValue(index + delta);
    coordinateB.get('displayOrder').setValue(index);
    this.coordinates.setControl(index + delta, coordinateA);
    this.coordinates.setControl(index, coordinateB);
    this.updateChart();
  }

  removeCoordinate(index: number) {
    this.coordinates.removeAt(index);
    for (let j = index; j < this.coordinates.length; j++) {
      this.coordinates.at(j)
        .get('displayOrder').setValue(j);
    }
    this.updateChart();
  }

  dropCoordinate(index: number, latitude: number, longitude: number) {
    console.log('here!');
    const coordinate = this.coordinates.at(index);
    coordinate.get('latitude').setValue(latitude);
    coordinate.get('longitude').setValue(longitude);
  }
}
