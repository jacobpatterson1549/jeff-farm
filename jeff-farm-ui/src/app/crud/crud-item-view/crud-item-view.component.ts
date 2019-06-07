import { Component, OnInit } from '@angular/core';

import { CrudItem } from '../crud.item';
import { CrudService } from '../crud.service';
import { FormItemType } from '../form.item';

@Component({
  selector: 'app-crud-item-view', // TODO: needed? (and other view/form components)
  templateUrl: './crud-item-view.component.html',
})
export class CrudItemViewComponent<T extends CrudItem> implements OnInit {

  crudItem: T;
  displayFieldNames: string[];
  displayFormItemTypes: object;
  formItemType = FormItemType; // used for the ngSwitch in the template

  constructor(private crudService: CrudService<T>) { }

  ngOnInit() {
    this.crudService.get()
      .subscribe((crudItem: T) => {
        this.crudItem = crudItem;
        this.displayFieldNames = crudItem.getDisplayFieldNames();

        this.displayFormItemTypes = crudItem.getFormItems()
          .filter(formItem => this.displayFieldNames.indexOf(formItem.name) >= 0)
          .reduce((obj, formItem) => {
            obj[formItem.name] = formItem.type;
            return obj;
          }, {});
      });
  }
}
