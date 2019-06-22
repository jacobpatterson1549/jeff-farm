import { catchError } from 'rxjs/operators';

import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { CrudItem } from '../crud-item';
import { CrudItemService } from '../crud-item.service';

@Component({
  templateUrl: './crud-delete.component.html',
})
export class CrudDeleteComponent<T extends CrudItem> {

  @Input() name: string;
  type: string;
  working = false;

  constructor(
    public modal: NgbActiveModal,
    private router: Router,
    private crudItemService: CrudItemService<T>) {

    this.type = this.crudItemService.getTypeName();
  }

  cancel() {
    this.modal.dismiss();
  }

  ok() {
    this.working = true;
    this.crudItemService.delete()
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
