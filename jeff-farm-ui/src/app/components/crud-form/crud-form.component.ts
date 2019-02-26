import { Component, OnInit, Input } from '@angular/core';
import { FormType } from 'src/app/classes/form.type';
import { CrudService } from 'src/app/services/crud.service';
import { CrudItem } from 'src/app/classes/crud.item';

@Component({
  selector: 'app-crud-form',
  templateUrl: './crud-form.component.html',
  styleUrls: ['./crud-form.component.css']
})
export class CrudFormComponent<T extends CrudItem> implements OnInit {

  @Input() crudService: CrudService<T>;
  @Input() formType: FormType;

  constructor() { }

  ngOnInit() {
  }

}
