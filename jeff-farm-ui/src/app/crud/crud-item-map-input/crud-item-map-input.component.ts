import * as Highcharts from 'highcharts';

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
  formItemType = FormItemType; // used for the ngSwitch in the template
  selectTargets: object = {};
  objectKeys = Object.keys; // used in the template
  private canAddCoordinate = false;
  public options = {
    chart: {
      type: 'scatter',
    },
    title: {
      text: 'Coordinate Map',
    },
    credits: {
      enabled: false
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
      }
    },
    series: [],
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
    if (this.coordinates.enabled) {
      this.options.title.text = 'Editable' + this.options.title.text;
      this.options.plotOptions.scatter.allowPointSelect = true;
      // TODO: connect last point to first.
    }

    this.options.series[0] = { data: [] };
    for (const coordinate of this.coordinates.controls) {
      this.options.series[0].data.push([
        coordinate.get('longitude').value,
        coordinate.get('latitude').value,
      ]);
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
    if (this.canAddCoordinate) { // TODO: popup if false
      navigator.geolocation.getCurrentPosition((position: Position) => {
        const coordinate = new CrudItemCoordinate(this.crudForm.get('id').value);
        coordinate.latitude = position.coords.latitude;
        coordinate.longitude = position.coords.longitude;
        coordinate.displayOrder = this.coordinates.length;
        this.coordinates.push(coordinate.getFormGroup(this.fb));
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
  }

  removeCoordinate(index: number) {
    this.coordinates.removeAt(index);
    for (let j = index; j < this.coordinates.length; j++) {
      this.coordinates.at(j)
        .get('displayOrder').setValue(j);
    }
  }
}
