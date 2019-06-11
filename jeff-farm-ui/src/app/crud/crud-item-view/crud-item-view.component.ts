import { Component, OnInit, Input } from '@angular/core';

import { CrudItem } from '../crud.item';
import { FormItemType } from '../form.item';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-crud-item-view',
  templateUrl: './crud-item-view.component.html',
  styleUrls: ['./crud-item-view.component.css']
})
export class CrudItemViewComponent<T extends CrudItem> implements OnInit {

  displayFieldNames: string[];
  displayFormItemTypes: object;
  formItemType = FormItemType; // used for the ngSwitch in the template
  @Input()
  crudItem: T;
  @Input()
  private hideDates ? = 'false';

  constructor(
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.displayFieldNames = this.crudItem.getDisplayFieldNames(this.hideDates !== '');

    this.displayFormItemTypes = this.crudItem.getFormItems()
      .filter(formItem => this.displayFieldNames.indexOf(formItem.name) >= 0)
      .reduce((obj, formItem) => {
        obj[formItem.name] = formItem.type;
        return obj;
      }, {});
  }

  goToInspection(inspectionId: number) {
    this.router.navigate(['../..', inspectionId], { relativeTo: this.route.parent });
  }
}
