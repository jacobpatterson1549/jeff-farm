import { Component, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { CrudService } from '../crud.service';
import { CrudItem } from '../crud.item';

@Component({
  templateUrl: './crud-delete.component.html',
})
export class CrudDeleteComponent<T extends CrudItem> {

  @Input() name: string;
  type: string;

  constructor(
    public modal: NgbActiveModal,
    private router: Router,
    private route: ActivatedRoute,
    private crudService: CrudService<T>) {

    this.type = this.crudService.getSingularName();
  }

  cancel() {
    this.modal.dismiss()
  }

  ok() {
    this.crudService.delete()
      .subscribe(_ => {
        this.router.navigate(['..'], { relativeTo: this.route.parent });
        this.modal.close();
      });
  }
}
