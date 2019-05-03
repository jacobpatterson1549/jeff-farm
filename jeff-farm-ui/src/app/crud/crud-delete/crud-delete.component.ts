import { Component, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { catchError } from 'rxjs/operators';

import { CrudService } from '../crud.service';
import { CrudItem } from '../crud.item';

@Component({
  templateUrl: './crud-delete.component.html',
})
export class CrudDeleteComponent<T extends CrudItem> {

  @Input() name: string;
  type: string;
  working: boolean = false;

  constructor(
    public modal: NgbActiveModal,
    private router: Router,
    private crudService: CrudService<T>) {

    this.type = this.crudService.getSingularName();
  }

  cancel() {
    this.modal.dismiss()
  }

  ok() {
    this.working = true;
    this.crudService.delete()
      .pipe(catchError((error: Error) => {
        this.working = false;
        this.modal.close(error.message);
        throw error;
      }))
      .subscribe(_ => {
        const url: string = this.router.url;
        const lastSlashIndex: number = url.lastIndexOf('/');
        const parentUrl: string = url.substring(0, lastSlashIndex);
        this.router.navigateByUrl(parentUrl);
        
        this.modal.close();
      });
  }
}
