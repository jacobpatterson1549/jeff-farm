import { Component, OnInit } from '@angular/core';
import { CrudFormEditor } from '../crud-form/crud-form-editor';
import { CrudItemFormComponent } from '../crud-item-form/crud-item-form.component';
import { CrudItem } from '../crud.item';
import { CrudItemGroup } from '../crud.item.group';
import { CrudService } from '../crud.service';
import { FormType } from '../form.type';

@Component({
  selector: 'app-crud-item-group-form',
  templateUrl: './crud-item-group-form.component.html',
})
export class CrudItemGroupFormComponent<U extends CrudItem, V extends CrudItem, T extends CrudItemGroup<V>>
  extends CrudItemFormComponent<T>
  implements CrudFormEditor<T>, OnInit {

  constructor(crudService: CrudService<T>) {
    super(crudService);
  }

  ngOnInit() {
  }


  setFormType(formType: FormType) { }

  isValid(): boolean {
    return false;
  }

  getCrudItem(): T {
    return null;
  }
}
