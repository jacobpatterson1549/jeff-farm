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
  // @ViewChildren(CrudItemInputComponent) groupEditors: QueryList<CrudItemInputComponent<T>>;

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
        this.selectTargets[0] = '';
        for (const [targetId, targetName] of Object.entries(targets)) {
          this.selectTargets[+targetId] = targetName;
        }
      });
    this.canAddCoordinate = 'geolocation' in navigator; // (also must be https)
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
        this.coordinates.push(coordinate.getFormGroup(this.fb));
      });
    }
  }
}
