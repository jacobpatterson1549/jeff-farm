import { Component, OnInit, Input } from '@angular/core';

import { CrudItem } from '../crud.item';
import { FormItemType } from '../form.item';

@Component({
  selector: 'app-crud-item-view',
  templateUrl: './crud-item-view.component.html',
})
export class CrudItemViewComponent<T extends CrudItem> implements OnInit {

  displayFieldNames: string[];
  displayFormItemTypes: object;
  formItemType = FormItemType; // used for the ngSwitch in the template
  @Input()
  crudItem: T;
  @Input()
  private viewDates ? = true;

  constructor() { }

  ngOnInit() {
    this.displayFieldNames = this.crudItem.getDisplayFieldNames(this.viewDates);

    this.displayFormItemTypes = this.crudItem.getFormItems()
      .filter(formItem => this.displayFieldNames.indexOf(formItem.name) >= 0)
      .reduce((obj, formItem) => {
        obj[formItem.name] = formItem.type;
        return obj;
      }, {});
  }
}
