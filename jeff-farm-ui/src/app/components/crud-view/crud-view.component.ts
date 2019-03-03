import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

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
  @Input() children: string[];
  item: T;
  canDelete: boolean = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.crudService.get()
      .subscribe(item => { this.item = item });
    
    this.crudService.canDelete()
      .subscribe(canDelete => { this.canDelete = canDelete });
  }

  deleteItem() {
    if (window.confirm('Really Delete?')) {
      this.crudService.delete()
        .subscribe(result => { this.router.navigate([".."], { relativeTo: this.route }) });
    }
  }
}
