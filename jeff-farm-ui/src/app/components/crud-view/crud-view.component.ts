import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';

import { CrudItem } from '../../classes/crud.item';
import { CrudService } from '../../services/crud.service';

@Component({
  selector: 'app-crud-view',
  templateUrl: './crud-view.component.html',
  styleUrls: ['./crud-view.component.css']
})
export class CrudViewComponent<T extends CrudItem> implements OnInit {

  @Input() crudService: CrudService<T>;
  @Input() itemNames: string[];
  item: T;

  constructor(
    private route: ActivatedRoute,
    private router: Router) { }


  ngOnInit() {
    const idParam: string = this.route.snapshot.paramMap.get('id');
    const id : number = parseInt(idParam);
    this.crudService.get(id)
      .subscribe(item => {
         this.item = item
         });
  }

  deleteItem() {
    if (window.confirm('Really Delete?')) {
      const idParam: string = this.route.snapshot.paramMap.get('id');
      const id : number = parseInt(idParam);
      this.crudService.delete(id)
        .subscribe(result => { this.router.navigate([".."], { relativeTo: this.route }) });
    }
  }
}
