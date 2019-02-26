import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-page-not-found',
  template: '<h2>Page not found</h2>',
  styles: ['h2 { color: red; }']
})
export class PageNotFoundComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
