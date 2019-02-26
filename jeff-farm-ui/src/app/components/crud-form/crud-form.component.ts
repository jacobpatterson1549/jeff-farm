import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { CrudItem } from 'src/app/classes/crud.item';
import { CrudService } from 'src/app/services/crud.service';
import { FormType } from 'src/app/classes/form.type';
import { FormItem } from 'src/app/classes/form.item';

@Component({
  selector: 'app-crud-form',
  templateUrl: './crud-form.component.html',
  styleUrls: ['./crud-form.component.css']
})
export class CrudFormComponent<T extends CrudItem> implements OnInit {

  @Input() crudService: CrudService<T>;
  @Input() formType: FormType;
  @Input() formItems: FormItem[];
  @Input() crudItem: T
  submitValue: string;

  constructor(private route: ActivatedRoute, private location: Location) { }

  ngOnInit() {

    this.submitValue = (this.formType === FormType.Update) ? 'Update' : 'Submit';

    if (this.formType == FormType.Create) {
      this.formItems = this.crudItem.getFormItems();
    }
    if (this.formType == FormType.Update) {
      const id = this.route.snapshot.paramMap.get('id');
      this.crudService.get(id)
        .subscribe((data: CrudItem) => {
          this.crudItem = Object.assign(this.crudItem, data)
          this.formItems = this.crudItem.getFormItems();
        });
    }
  }

  submitForm() {

    for (let formItem of this.formItems) {
      this.crudItem[formItem.name] = formItem.value;
    }

    if (this.formType == FormType.Create) {
      this.crudService.create(this.crudItem)
        .subscribe(result => { location.replace('..') });
    }
    if (this.formType == FormType.Update) {
      this.crudService.update(this.crudItem)
        .subscribe(result => { location.replace('../..') });
    }
  }
}
