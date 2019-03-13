import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { CrudService, CrudChild } from '../crud.service';
import { CrudItem } from '../crud.item';
import { FormItem, FormItemType } from '../form.item';

@Component({
  selector: 'crud-view',
  templateUrl: './crud-view.component.html',
  styleUrls: ['./crud-view.component.css']
})
export class CrudViewComponent<T extends CrudItem> implements OnInit {

  crudItem: T;
  displayFieldNames: string[];
  displayFormItemTypes: object;
  formItemType = FormItemType; // used for the ngSwitch in the template
  canDelete: boolean = false;
  crudChildren: CrudChild[];
  crudItemSingularName: string;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private crudService: CrudService<T>) { }

  ngOnInit() {
    
    this.crudChildren = this.crudService.getCrudChildren();
    this.crudItemSingularName = this.crudService.getSingularName();
    
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
    
    this.crudService.canDelete()
      .subscribe((canDelete: boolean) => this.canDelete = canDelete);
  }

  deleteCrudItem() {
    if (window.confirm('Really Delete?')) {
      this.crudService.delete()
        .subscribe(_ => this.router.navigate(['..'], { relativeTo: this.route.parent }) );
    }
  }
}
