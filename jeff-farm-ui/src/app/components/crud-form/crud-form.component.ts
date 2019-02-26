import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable, of } from 'rxjs';

import { CrudItem } from 'src/app/classes/crud.item';
import { CrudService } from 'src/app/services/crud.service';
import { FormType } from 'src/app/classes/form.type';
import { FormItem } from 'src/app/classes/form.item';
import { Farm } from 'src/app/classes/farm';

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

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
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
}
