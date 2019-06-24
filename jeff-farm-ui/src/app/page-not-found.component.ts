import { Component } from '@angular/core';

@Component({
  selector: 'app-page-not-found',
  template: '<ngb-alert [dismissible]="false" type="danger">Page not found</ngb-alert>',
})
export class PageNotFoundComponent { }
