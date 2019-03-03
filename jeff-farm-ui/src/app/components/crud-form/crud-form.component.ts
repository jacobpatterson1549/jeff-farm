import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { CrudItem } from '../../classes/crud.item';
import { CrudService } from '../../services/crud.service';
import { FormType } from '../../classes/form.type';
import { FormItem, FormItemType } from '../../classes/form.item';
import { Observable } from 'rxjs';

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
  formItemType = FormItemType; // used for the ngSwitch in the template

  constructor(
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {

    this.submitValue = (this.formType === FormType.Update) ? 'Update' : 'Submit';

    if (this.formType == FormType.Create) {
      this.formItems = this.crudItem.getFormItems();
    }
    if (this.formType == FormType.Update) {
      this.crudService.get()
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

    let result: Observable<Object> = null;
    if (this.formType == FormType.Create) {
      result = this.crudService.create(this.crudItem);
    }
    if (this.formType == FormType.Update) {
      result = this.crudService.update(this.crudItem);
    }
    result.subscribe(result => { this.router.navigate([".."], { relativeTo: this.route }) });
  }
}
